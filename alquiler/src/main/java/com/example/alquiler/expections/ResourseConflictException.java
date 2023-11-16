package com.example.alquiler.expections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
@Getter
@Setter
public class ResourseConflictException extends RuntimeException{
    private String message;

    public ResourseConflictException(String message) {
        super(message);
        this.message = message;
    }
}
