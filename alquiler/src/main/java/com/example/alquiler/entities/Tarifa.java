package com.example.alquiler.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TARIFAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tarifa {
    @Id
    private long id;
    @Column(name = "TIPO_TARIFA")
    private int tipoTarifa;
    @Column(name = "DEFINICION")
    private String definicion;
    @Column(name = "DIA_SEMANA")
    private int diaSemana;
    @Column(name = "DIA_MES")
    private Integer diaMes;
    @Column(name = "MES")
    private Integer mes;
    @Column(name = "ANIO")
    private Integer anio;
    @Column(name = "MONTO_FIJO_ALQUILER")
    private double montoFijoAlquiler;
    @Column(name = "MONTO_MINUTO_FRACCION")
    private double montoMinutoFraccion;
    @Column(name = "MONTO_KM")
    private double montoKm;
    @Column(name = "MONTO_HORA")
    private double montoHora;
}
