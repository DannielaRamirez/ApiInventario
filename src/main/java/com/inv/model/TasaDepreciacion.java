package com.inv.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TasaDepreciacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NonNull
    private Integer año;
    @NonNull
    private Double tasaDepreciacion;
    @ManyToOne
    @JoinColumn(name = "clasificacionActivoId")
    private ClasificacionActivo clasificacionActivo;



}