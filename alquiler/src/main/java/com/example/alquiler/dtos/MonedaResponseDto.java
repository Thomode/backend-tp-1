package com.example.alquiler.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MonedaResponseDto {
    private String moneda;
    private Double importe;
}
