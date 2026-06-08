package br.com.ifba.infraestructure.exception;


import jakarta.validation.ValidationException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {



    @Value("${server.error.include.exception:false}")
    private boolean printStackTrace;


    /** Trata exceções genéricas ...*/
    /**
     * Trata exceções de validação de campos (@Valid)
     * Sobrescrevendo corretamente o método da superclasse ResponseEntityExceptionHandler
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));

        String fieldsMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ValidationExceptionDetails details = ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title("Bad Request Exception, Campos Fields")
                .details("Cheque os campo(s) com erros")
                .developerMessage(ex.getClass().getName())
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build();

        return new ResponseEntity<>(details, headers, status);
    }


    @ExceptionHandler(BusinessException.class)
    @ResponseStatus( HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleBusinessException(
            final BusinessException businessException,
    final WebRequest request) {

        final String mensagemErro = businessException.getMessage();

        logger.error(mensagemErro, businessException);

        return construirMensagemDeErro(
                businessException,
                mensagemErro,
                HttpStatus.BAD_REQUEST,
                request);
    }



    private ResponseEntity<Object> construirMensagemDeErro(
            final Exception exception,
            final String message,
            final HttpStatus httpStatus,
            final WebRequest request) {
        
ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (this.printStackTrace) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(exception));
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}
