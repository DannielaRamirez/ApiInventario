package com.inv.controller.dto;

import com.inv.model.ActivoStatus;
import com.inv.model.ClasificacionActivo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ActivoDto {
    private String nombre;
    private String serial;
    private String descripcion;
    private LocalDate fechaCompra;
    private double valorCompra;
    private String status;
    private LocalDate fechaBaja;
    private ClasificacionActivo clasificacionActivo;
    private DepreciacionActivoDto depreciacionActivoDto;

    public ActivoStatus activoStatus(){
        return ActivoStatus.valueOf(getStatus());
    }

    public boolean hasNombre() {
        return nombre != null;
    }

    public boolean hasSerial() {
        return serial != null;
    }

    public boolean hasDescripcion() {
        return descripcion != null;
    }

    public boolean hasFechaCompra() {
        return fechaCompra != null;
    }

    public boolean hasValorCompra() {
        return valorCompra != 0;
    }

    public boolean hasStatus() {
        return status != null;
    }

    public boolean hasFechaBaja() {
        return fechaBaja != null;
    }

    public boolean hasClasificacionActivo() {
        return clasificacionActivo != null;
    }
}
