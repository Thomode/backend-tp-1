package com.example.estacion.expections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@Getter
@Setter
public class ResourceNotFoundException extends RuntimeException {
    public String message;

    public ResourceNotFoundException(String message) {
        super(message);
        this.message = message;
    }
}
