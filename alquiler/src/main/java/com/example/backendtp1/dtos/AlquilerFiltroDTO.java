package com.example.backendtp1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlquilerFiltroDTO {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
}
