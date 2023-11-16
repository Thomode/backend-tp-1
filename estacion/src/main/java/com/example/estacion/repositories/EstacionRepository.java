package com.example.estacion.repositories;

import com.example.estacion.entities.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    @Query(value = "SELECT COALESCE(MAX(E.id), 0) FROM ESTACIONES E")
    Integer getIdMaximo();
}
