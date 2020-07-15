package br.com.restfull.exception.handler;

import br.com.restfull.exception.ExceptionResponse;
import br.com.restfull.exception.InvalidJwtAuthenticationException;
import br.com.restfull.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestexceptions(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidJwtAuthenticationException.class)
    public final ResponseEntity<ExceptionResponse> InvalidJwtAuthenticationException(Exception e, WebRequest request) {
        ExceptionResponse exceptionResponse =
                new ExceptionResponse(
                        new Date(),
                        e.getMessage(),
                        request.getDescription(false));
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
