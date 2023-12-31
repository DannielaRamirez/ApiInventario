package com.inv.execption;

import lombok.Data;

@Data
public class ErrorResponse {
    private int status;
    private String message;
    private ErrorDetails errorDetails;

    public ErrorResponse(ErrorDetails errorDetails) {
        this.errorDetails = errorDetails;
        this.status = errorDetails.getStatus();
        this.message= errorDetails.getMessage();
    }
}
