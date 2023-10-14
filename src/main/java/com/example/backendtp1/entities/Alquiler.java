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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ID_CLIENTE")
    private String idCliente;

    @Column(name = "ESTADO")
    private int estado;

    @Column(name = "ESTADO_RETIRO")
    private int estadoRetiro;

    @Column(name = "ESTADO_DEVOLUCION")
    private int estadoDevolucion;

    @Column(name = "FECHA_HORA_RETIRO")
    private LocalDate fechaHoraRetiro;

    @Column(name = "FECHA_HORA_DEVOLUCION")
    private LocalDate getFechaHoraDevolucion;

    @Column(name = "MONTO")
    private double monto;

    @OneToOne
    @JoinColumn(name = "ID_TARIFA")
    private Tarifa tarifa;
}
