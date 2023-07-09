package com.inv.controller;

import com.inv.controller.dto.ActivoDto;
import com.inv.execption.NotFoundException;
import com.inv.execption.ErrorDetails;
import com.inv.execption.ExceptionHandlerUtil;
import com.inv.model.Activo;
import com.inv.model.ActivoStatus;
import com.inv.model.ClasificacionActivo;
import com.inv.repository.ActivoRepository;
import com.inv.repository.ClasificacionActivoRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("activo")
public class ActivoController {

    private final ActivoRepository activoRepository;
    private final ClasificacionActivoRepository clasificacionActivoRepository;
    private final HttpServletRequest request;

    @Autowired
    public ActivoController(ActivoRepository activoRepository,ClasificacionActivoRepository clasificacionActivoRepository, HttpServletRequest request) {
        this.activoRepository = activoRepository;
        this.clasificacionActivoRepository= clasificacionActivoRepository;
        this.request= request;
    }

    @PostMapping
    public Long createActivo(@RequestBody @Valid ActivoDto dto) {
            validateActivo(dto);
            Activo activoBuilder = new Activo().builder().nombre(dto.getNombre())
                    .serial(dto.getSerial())
                    .descripcion(dto.getDescripcion())
                    .fechaCompra(dto.getFechaCompra())
                    .valorCompra(dto.getValorCompra())
                    .clasificacionActivo(dto.getClasificacionActivo())
                    .status(ActivoStatus.CREATE)
                    .build();
            Activo activo = activoRepository.save(activoBuilder);
        return activo.getId();
    }


    @PutMapping("/{id}")
    public Long editActivo(@PathVariable @NotNull Long id, @RequestBody @Valid ActivoDto dto) {
        validateActivo(dto);
        Optional<Activo> activoFound = activoRepository.findById(id);
        if (activoFound.isPresent()) {
            Activo activo = activoFound.get();
            if (dto.hasStatus()) {
                ActivoStatus status = ActivoStatus.valueOf(dto.getStatus());
                activo.setStatus(status);
            }
            activo.update(dto);
            activoRepository.save(activo);
            return activo.getId();
        } else {
            ErrorDetails errorDetails = ExceptionHandlerUtil.createErrorDetails(HttpStatus.NOT_FOUND, new NotFoundException("No existe activo a editar con Id : " + id), request);
            throw new NotFoundException(errorDetails);
        }
    }

    public void validateActivo(ActivoDto dto) {
        ClasificacionActivo clasificacionActivoDto = dto.getClasificacionActivo();
        Optional<ClasificacionActivo> clasificacionActivo = clasificacionActivoRepository.findById(clasificacionActivoDto.getId());
        if (!clasificacionActivo.isPresent()) {
            ErrorDetails errorDetails = ExceptionHandlerUtil.createErrorDetails(HttpStatus.NOT_FOUND, new NotFoundException("No existe Clasificacion con Id : " + dto.getClasificacionActivo().getId()), request);
            throw new NotFoundException(errorDetails);
        }else{
            try {
                ActivoStatus statusEnum = ActivoStatus.valueOf(dto.getStatus());
                boolean isValidStatus = statusEnum == ActivoStatus.CREATE ||
                        statusEnum == ActivoStatus.ACTIVO ||
                        statusEnum == ActivoStatus.BAJA;

                if (!isValidStatus) {
                    ErrorDetails errorDetails = ExceptionHandlerUtil.createErrorDetails(HttpStatus.BAD_REQUEST, new NotFoundException("El campo 'status' no es válido los valores validos son: CREATE,ACTIVO,BAJA"), request);
                    throw new NotFoundException(errorDetails);
                }
            }catch(IllegalArgumentException ex) {
                ErrorDetails errorDetails = ExceptionHandlerUtil.createErrorDetails(HttpStatus.BAD_REQUEST, new NotFoundException("El campo 'status' no es válido. Los valores válidos son: CREATE, ACTIVO, BAJA"), request);
                throw new NotFoundException(errorDetails);
            }
        }
    }
}
