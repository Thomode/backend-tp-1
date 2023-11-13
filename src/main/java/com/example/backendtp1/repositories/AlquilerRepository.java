package com.example.backendtp1.repositories;

import com.example.backendtp1.entities.Alquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AlquilerRepository extends JpaRepository<Alquiler, Integer> {

    @Query(value = "SELECT COALESCE(MAX(a.id), 0) FROM ALQUILERES a")
    Integer getIdMaximo();
}
