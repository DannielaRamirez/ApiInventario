package com.inv.model;

import com.inv.controller.dto.ActivoDto;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Activo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private String nombre;
    private String serial;
    private String descripcion;
    private LocalDate fechaCompra;
    @NonNull
    private Double valorCompra;
    private ActivoStatus status;
    private LocalDate fechaBaja;

    @ManyToOne
    private ClasificacionActivo clasificacionActivo;


    public void update(ActivoDto dto){
        if (dto.hasNombre()) {
            nombre = dto.getNombre();
        }
        if (dto.hasSerial()) {
            serial = dto.getSerial();
        }
        if (dto.hasDescripcion()) {
            descripcion = dto.getDescripcion();
        }
        if (dto.hasFechaCompra()) {
            fechaCompra = dto.getFechaCompra();
        }
        if (dto.hasValorCompra()) {
            valorCompra = dto.getValorCompra();
        }
        if (dto.hasStatus()) {
            status = dto.activoStatus();
        }
        if (dto.hasFechaBaja()) {
            fechaBaja = dto.getFechaBaja();
        }
        if (dto.hasClasificacionActivo()) {
            clasificacionActivo = dto.getClasificacionActivo();
        }

    }
}
