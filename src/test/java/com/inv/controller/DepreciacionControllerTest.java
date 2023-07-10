package com.inv.controller;

import com.inv.controller.dto.ActivoDto;
import com.inv.execption.ErrorDetails;
import com.inv.execption.ExceptionHandlerUtil;
import com.inv.execption.NotFoundException;
import com.inv.model.Activo;
import com.inv.repository.ActivoRepository;
import com.inv.service.DepreciacionService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;



public class DepreciacionControllerTest {

    private DepreciacionController depreciacionController;
    private ActivoRepository activoRepository;
    private DepreciacionService depreciacionService;
    private HttpServletRequest request;
    private ExceptionHandlerUtil exceptionHandlerUtil;

    @BeforeEach
    public void setup() {
        activoRepository = mock(ActivoRepository.class);
        depreciacionService = mock(DepreciacionService.class);
        request = mock(HttpServletRequest.class);
        exceptionHandlerUtil = mock(ExceptionHandlerUtil.class);

        depreciacionController = new DepreciacionController(activoRepository, exceptionHandlerUtil,
                depreciacionService, request);
    }

    @Test
    public void testCalcularDepreciacion() {

        long id = 1L;
        Activo activo = new Activo();
        ActivoDto expectedDto = new ActivoDto();

        when(activoRepository.findById(id)).thenReturn(Optional.of(activo));
        when(depreciacionService.calcularDepreciacion(activo)).thenReturn(expectedDto);

        ResponseEntity<ActivoDto> response = depreciacionController.calcularDepreciacion(id);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedDto, response.getBody());
        verify(activoRepository).findById(id);
        verify(depreciacionService).calcularDepreciacion(activo);
        verifyNoMoreInteractions(activoRepository, depreciacionService);
    }

}





