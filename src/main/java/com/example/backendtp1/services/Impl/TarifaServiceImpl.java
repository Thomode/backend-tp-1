package com.example.backendtp1.services.Impl;

import com.example.backendtp1.entities.Tarifa;
import com.example.backendtp1.repositories.TarifaRespository;
import com.example.backendtp1.services.TarifaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarifaServiceImpl implements TarifaService {
    private final TarifaRespository tarifaRespository;

    public TarifaServiceImpl(TarifaRespository tarifaRespository){
        this.tarifaRespository = tarifaRespository;
    }

    @Override
    public Tarifa getById(Long id) {
        return tarifaRespository.findById(id).orElse(null);
    }

    public List<Tarifa> getAll() {
        return tarifaRespository.findAll();
    }
}
