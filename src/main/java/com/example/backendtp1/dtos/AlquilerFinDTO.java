package com.example.backendtp1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerFinDTO {
    private Integer idAlquiler;
    private String tipoMoneda;
    private long idEstacionDevolucion;
}
