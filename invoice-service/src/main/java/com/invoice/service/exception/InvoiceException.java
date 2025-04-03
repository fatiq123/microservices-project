package com.invoice.service.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class InvoiceException extends RuntimeException {

    private HttpStatus httpStatus;
    private String errorMessage;
    private List<String> errorDetails;

    public InvoiceException(HttpStatus httpStatus, String errorMessage, List<String> errorDetails) {
        super(errorMessage  );
        this.httpStatus = httpStatus;
        this.errorMessage = errorMessage;
        this.errorDetails = errorDetails;
    }
}
