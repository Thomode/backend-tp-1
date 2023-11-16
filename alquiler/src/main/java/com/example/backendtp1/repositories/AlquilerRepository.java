package com.example.backendtp1.repositories;

import com.example.backendtp1.entities.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    @Query(value = "SELECT COALESCE(MAX(a.id), 0) FROM ALQUILERES a")
    Integer getIdMaximo();

    @Query(value = "SELECT a FROM ALQUILERES a " +
            "WHERE a.monto BETWEEN :montoInicio AND :montoFin " +
            "AND a.estado = 2")
    List<Alquiler> findAlquileres(double montoInicio, double montoFin);

}
