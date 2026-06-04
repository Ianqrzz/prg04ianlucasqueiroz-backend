package br.com.ifba.infraestructure.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{
    private int status;
    private String message;
    private String stackTrace;


    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
