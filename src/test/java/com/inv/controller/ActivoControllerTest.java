package com.inv.controller;

import com.inv.controller.dto.ActivoDto;
import com.inv.execption.ExceptionHandlerUtil;
import com.inv.model.Activo;
import com.inv.model.ClasificacionActivo;
import com.inv.repository.ActivoRepository;
import com.inv.repository.ClasificacionActivoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;



public class ActivoControllerTest {

    private ActivoController activoController;
    private ActivoRepository activoRepository;
    private ClasificacionActivoRepository clasificacionActivoRepository;
    private HttpServletRequest request;
    private ExceptionHandlerUtil exceptionHandlerUtil;

    @BeforeEach
    public void setup() {
        activoRepository = mock(ActivoRepository.class);
        clasificacionActivoRepository = mock(ClasificacionActivoRepository.class);
        request = mock(HttpServletRequest.class);
        exceptionHandlerUtil = mock(ExceptionHandlerUtil.class);
        activoController = new ActivoController(activoRepository, clasificacionActivoRepository, request);
    }

    @Test
    public void testCreateActivo_ReturnsActivoId() {
        ActivoDto dto = buildActivo();
        Activo activo = new Activo();
        when(clasificacionActivoRepository.findById(1L)).thenReturn(Optional.of(dto.getClasificacionActivo()));
        when(activoRepository.save(any(Activo.class))).thenReturn(activo);
        Long result = activoController.createActivo(dto);
        assertEquals(activo.getId(), result);
        verify(activoRepository).save(any(Activo.class));
    }


    @Test
    public void testEditActivoReturnId() {
        ActivoDto dto = buildActivo();
        Long id = 1L;
        Activo activo = new Activo();
        activo.setId(id);

        Optional<Activo> optionalActivo = Optional.of(activo);
        when(clasificacionActivoRepository.findById(1L)).thenReturn(Optional.of(dto.getClasificacionActivo()));
        when(activoRepository.findById(id)).thenReturn(optionalActivo);
        when(activoRepository.save(any(Activo.class))).thenReturn(activo);

        Long result = activoController.editActivo(id, dto);

        assertEquals(activo.getId(), result);
        verify(activoRepository).findById(id);
        verify(activoRepository).save(any(Activo.class));
    }

    public ActivoDto buildActivo(){
        ActivoDto dto = new ActivoDto();
        dto.setNombre("Activo1");
        dto.setSerial("Serial123");
        dto.setNombre("Activo1");
        dto.setSerial("Serial123");
        ClasificacionActivo clasificacionActivoDto = new ClasificacionActivo();
        clasificacionActivoDto.setId(1L);
        dto.setClasificacionActivo(clasificacionActivoDto);
        dto.setStatus("ACTIVO");
        return  dto;
    }
 }

