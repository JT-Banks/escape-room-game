package com.thefinaldoor.core.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
class ErrorResponse {

    private String message;

    private HttpStatus httpStatus;

    public ErrorResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
