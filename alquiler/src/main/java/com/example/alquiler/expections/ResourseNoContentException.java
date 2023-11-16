package com.example.alquiler.expections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
@Getter
@Setter
public class ResourseNoContentException extends RuntimeException{
    private String message;

    public ResourseNoContentException(String message) {
        super(message);
        this.message = message;
    }
}
