package com.example.backendtp1.services.Impl;

import com.example.backendtp1.entities.Alquiler;
import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.repositories.AlquilerRepository;
import com.example.backendtp1.services.AlquilerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerImpl implements AlquilerService {
    private  final AlquilerRepository alquilerRepository;

    public AlquilerImpl(AlquilerRepository alquilerRepository){
        this.alquilerRepository = alquilerRepository;
    }
    @Override
    public Alquiler add(Alquiler entity) {
        return null;
    }

    @Override
    public Alquiler update(Alquiler entity) {
        return null;
    }

    @Override
    public Alquiler delete(Long aLong) {
        return null;
    }

    @Override
    public Alquiler getById(Long aLong) {
        return null;
    }

    @Override
    public List<Alquiler> getAll() {
        return alquilerRepository.findAll();
    }
}
