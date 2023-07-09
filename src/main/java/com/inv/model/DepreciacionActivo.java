package com.inv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DepreciacionActivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "activo_id")
    @NonNull
    private Activo activo;
    private int año;
    private double valorDepreciacion;

    public double calcularDepreciacion() {int year = LocalDate.now().getYear();
       Double tasaDepreciacion=  activo.getClasificacionActivo().getTasaDepreciacionActual();

        if (tasaDepreciacion == null) {
            throw new IllegalStateException("La tasa de depreciacion no se encuentra cargada para el año actual");
        }
        return activo.getValorCompra() * tasaDepreciacion;
    }
}


