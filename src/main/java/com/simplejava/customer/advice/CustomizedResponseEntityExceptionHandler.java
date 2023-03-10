package com.simplejava.customer.advice;

import com.simplejava.customer.exception.ApplicationException;
import com.simplejava.customer.exception.CustomerNotFoundException;
import com.simplejava.customer.exception.ErrorDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description : Global exception handler
 * User: Tanveer Haider
 * Date: 2/25/2023
 * Time: 12:53 AM
 */
@Log4j2
@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(ApplicationException.class)
    public final ResponseEntity<ErrorDetails> handleApplicationExceptions(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public final ResponseEntity<ErrorDetails> handleUserNotFoundException(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                ex.getMessage(), request.getDescription(false));

        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDetails> handleInvalidArgument(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errorMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errorMap.put(error.getField(), error.getDefaultMessage());
        });
        String errorMessage = convertMapToString(errorMap);
        ErrorDetails errorDetails = new ErrorDetails(LocalDateTime.now(),
                errorMessage, request.getDescription(false));
        return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.BAD_REQUEST);
    }


    protected String convertMapToString(Map<String, String> errorMap) {
        String errorMessage = errorMap.keySet().stream()
                .map(key -> key + "=" + errorMap.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return errorMessage;

    }


}
