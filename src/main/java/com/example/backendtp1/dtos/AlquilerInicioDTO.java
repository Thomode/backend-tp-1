package com.example.backendtp1.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
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
