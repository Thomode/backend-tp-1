package com.example.backendtp1.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity(name = "ALQUILERES" )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Alquiler {
    @Id
    private Integer id;

    @Column(name = "ID_CLIENTE")
    private String idCliente;

    @Column(name = "ESTADO")
    private int estado;

    @ManyToOne
    @JoinColumn(name = "ESTACION_RETIRO")
    private Estacion estacionRetiro;

    @ManyToOne
    @JoinColumn(name = "ESTACION_DEVOLUCION")
    private Estacion estacionDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDate fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDate fechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    @ManyToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa tarifa;
}
