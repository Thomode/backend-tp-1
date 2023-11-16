package com.example.alquiler.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class MonedaRequestDto {
    private String moneda_destino;
    private Double importe;
}
