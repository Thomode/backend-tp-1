package com.example.backendtp1.repositories;

import com.example.backendtp1.entities.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
    @Query(value = "SELECT COALESCE(MAX(E.id), 0) FROM ESTACIONES E")
    Integer getIdMaximo();
}
