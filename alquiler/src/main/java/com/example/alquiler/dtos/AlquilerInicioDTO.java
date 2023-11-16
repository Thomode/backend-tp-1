package com.example.alquiler.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerInicioDTO {
    private Long estacion_retiro;
    private Long tarifaId;
}
