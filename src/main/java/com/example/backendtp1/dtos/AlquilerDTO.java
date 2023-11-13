package com.example.backendtp1.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlquilerDTO {
    private Integer id;
    private String tipoModena;
    private long idEstacionDevolucion;
}
