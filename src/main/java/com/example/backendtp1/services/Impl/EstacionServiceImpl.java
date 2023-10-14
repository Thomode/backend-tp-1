package com.example.backendtp1.services.Impl;

import com.example.backendtp1.entities.Estacion;
import com.example.backendtp1.repositories.EstacionRepository;
import com.example.backendtp1.services.EstacionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EstacionServiceImpl implements EstacionService {
    private final EstacionRepository estacionRepository;

    public EstacionServiceImpl(EstacionRepository estacionRepository){
        this.estacionRepository = estacionRepository;
    }
    @Override
    public Estacion add(Estacion entity) {
        return null;
    }

    @Override
    public Estacion update(Estacion entity) {
        return null;
    }

    @Override
    public Estacion delete(Long aLong) {
        return null;
    }

    @Override
    public Estacion getById(Long aLong) {
        return null;
    }

    @Override
    public List<Estacion> getAll() {
        return estacionRepository.findAll();
    }
}
