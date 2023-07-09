package com.inv.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepreciacionActivoDto {
    private int año;
    private double valorDepreciacion;
}
