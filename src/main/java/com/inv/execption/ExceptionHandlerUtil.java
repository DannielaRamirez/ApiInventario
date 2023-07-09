package com.inv.execption;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExceptionHandlerUtil {

    public static ErrorDetails createErrorDetails(HttpStatus status, Exception ex, HttpServletRequest request) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setTimestamp(LocalDateTime.now());
        errorDetails.setStatus(status.value());
        errorDetails.setError(status.getReasonPhrase());
        errorDetails.setMessage(ex.getMessage());
        errorDetails.setPath(request.getRequestURI());
        return errorDetails;
    }
}

