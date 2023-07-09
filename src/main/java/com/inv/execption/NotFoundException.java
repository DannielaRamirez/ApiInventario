package com.inv.execption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    private ErrorDetails error;

    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(ErrorDetails error) {
        this.error = error;
    }

    public ErrorDetails getError() {
        return error;
    }
}
