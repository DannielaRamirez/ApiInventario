package com.inv.controller;

import com.inv.controller.dto.ActivoDto;
import com.inv.execption.ErrorDetails;
import com.inv.execption.ExceptionHandlerUtil;
import com.inv.execption.NotFoundException;
import com.inv.model.Activo;
import com.inv.repository.ActivoRepository;
import com.inv.service.DepreciacionService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("activo")
public class DepreciacionController {

    private final ActivoRepository activoRepository;
    private final HttpServletRequest request;
    private final DepreciacionService depreciacionService;

    private final ExceptionHandlerUtil exceptionHandlerUtil;

    public DepreciacionController(ActivoRepository activoRepository, ExceptionHandlerUtil exceptionHandlerUtil,
                                  DepreciacionService depreciacionService, HttpServletRequest request) {
        this.activoRepository = activoRepository;
        this.depreciacionService = depreciacionService;
        this.request = request;
        this.exceptionHandlerUtil = exceptionHandlerUtil;
    }

    @PostMapping("/depreciacion/{id}")
    public ResponseEntity<ActivoDto> calcularDepreciacion(@PathVariable @NotNull Long id) {
        {
            ActivoDto activoDto;
            Optional<Activo> optionalActivo = activoRepository.findById(id);
            if (optionalActivo.isPresent()) {
                Activo activo = optionalActivo.get();
                 activoDto = depreciacionService.calcularDepreciacion(activo);
            }
            else {
                ErrorDetails errorDetails = ExceptionHandlerUtil.createErrorDetails(HttpStatus.NOT_FOUND, new NotFoundException("No existe Activo con Id : " + id), request);
                throw new NotFoundException(errorDetails);
            }
            return new ResponseEntity<ActivoDto>(activoDto, HttpStatus.OK);
        }
    }
}







