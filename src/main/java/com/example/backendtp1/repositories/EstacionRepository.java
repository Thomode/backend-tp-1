package com.example.backendtp1.repositories;

import com.example.backendtp1.entities.Estacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstacionRepository extends JpaRepository<Estacion, Long> {
}
