package com.inv.service;

import com.inv.controller.dto.ActivoDto;
import com.inv.controller.dto.DepreciacionActivoDto;
import com.inv.execption.ErrorDetails;
import com.inv.execption.ExceptionHandlerUtil;
import com.inv.execption.NotFoundException;
import com.inv.model.Activo;
import com.inv.model.DepreciacionActivo;
import com.inv.model.TasaDepreciacion;
import com.inv.repository.DepreciacionActivoRepository;
import com.inv.repository.TasaDeprecicionRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DepreciacionService {

    private final TasaDeprecicionRepository tasaDeprecicionRepository;
    private final DepreciacionActivoRepository depreciacionActivoRepository;
    private final HttpServletRequest request;

    public DepreciacionService(TasaDeprecicionRepository tasaDeprecicionRepository, HttpServletRequest request,
                               DepreciacionActivoRepository depreciacionActivoRepository) {
        this.tasaDeprecicionRepository = tasaDeprecicionRepository;
        this.depreciacionActivoRepository = depreciacionActivoRepository;
        this.request= request;
    }

    public ActivoDto calcularDepreciacion(Activo activo) {
        validateActivo(activo);

        TasaDepreciacion depreciacionActual = getDepreciacionActual(activo);
        updateClasificacionActivoTasaDepreciacion(activo, depreciacionActual);

        DepreciacionActivo depreciacionActivo = getDepreciacionActivo(activo);
        calculateAndUpdateDepreciacionValue(depreciacionActivo);

        depreciacionActivoRepository.save(depreciacionActivo);

        return createActivoDto(activo, depreciacionActivo);
    }

    private void validateActivo(Activo activo) {
        if (activo == null) {
            throw new IllegalArgumentException("Activo cannot be null");
        }
    }

    private TasaDepreciacion getDepreciacionActual(Activo activo) {
        int currentYear = LocalDate.now().getYear();
        ErrorDetails errorDetails = ExceptionHandlerUtil.createErrorDetails(HttpStatus.NOT_FOUND,
                new NotFoundException("La tasa de depreciación para Equipo con serial " + activo.getSerial() +
                        " no se encuentra cargada para el año actual"), request);

        return tasaDeprecicionRepository.findByClasificacionActivo(activo.getClasificacionActivo())
                .stream()
                .filter(d -> d.getAño() == currentYear)
                .findFirst()
                .orElseThrow(() -> new NotFoundException(errorDetails));
    }


    private void updateClasificacionActivoTasaDepreciacion(Activo activo, TasaDepreciacion depreciacionActual) {
        activo.getClasificacionActivo().setTasaDepreciacionActual(depreciacionActual.getTasaDepreciacion());
    }

    private DepreciacionActivo getDepreciacionActivo(Activo activo) {
        DepreciacionActivo depreciacionActivo = depreciacionActivoRepository.findByActivoId(activo.getId());
        if (depreciacionActivo == null) {
            depreciacionActivo = new DepreciacionActivo();
            depreciacionActivo.setActivo(activo);
            depreciacionActivo.setAño(LocalDate.now().getYear());
        }
        return depreciacionActivo;
    }

    private void calculateAndUpdateDepreciacionValue(DepreciacionActivo depreciacionActivo) {
        double valorDepreciacion = depreciacionActivo.calcularDepreciacion();
        depreciacionActivo.setValorDepreciacion(valorDepreciacion);
    }

    private ActivoDto createActivoDto(Activo activo, DepreciacionActivo depreciacionActivo) {
        DepreciacionActivoDto depreciacionActivoDto = new DepreciacionActivoDto(
                depreciacionActivo.getAño(), depreciacionActivo.getValorDepreciacion());

        return ActivoDto.builder()
                .nombre(activo.getNombre())
                .serial(activo.getSerial())
                .descripcion(activo.getDescripcion())
                .fechaCompra(activo.getFechaCompra())
                .valorCompra(activo.getValorCompra())
                .status(String.valueOf(activo.getStatus()))
                .fechaBaja(activo.getFechaBaja())
                .clasificacionActivo(activo.getClasificacionActivo())
                .depreciacionActivoDto(depreciacionActivoDto)
                .build();
    }
}

