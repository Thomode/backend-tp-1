package com.example.alquiler.controllers;

import com.example.alquiler.dtos.EstacionResponseDto;
import lombok.NoArgsConstructor;
import org.springframework.web.reactive.function.client.WebClient;

@NoArgsConstructor
public class EstacionServiceComunicacion {
    private final String baseUrl = "http://localhost:4002";
    WebClient.Builder webClient = WebClient.builder();
    public EstacionResponseDto getById(Long id) {
        return webClient.baseUrl(baseUrl)
                .build()
                .get()
                .uri("/api/client/estacion/"+id)
                .retrieve()
                .bodyToMono(EstacionResponseDto.class)
                .block();
    }

    public Double getDistanciaEstaciones(Long idEstacion1, Long idEstacion2) {
        return webClient.baseUrl(baseUrl)
                .build()
                .get()
                .uri(uriBuilder ->
                        uriBuilder
                                .path("/api/client/estacion/distanciaEntreEstaciones")
                                .queryParam("idEstacion1", idEstacion1)
                                .queryParam("idEstacion2", idEstacion2)
                                .build()
                )
                .retrieve()
                .bodyToMono(Double.class)
                .block();
    }
}
