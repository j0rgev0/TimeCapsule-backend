package com.Timecapsule.timecapsule.config;

import com.Timecapsule.timecapsule.dto.ErrorDto;
import com.Timecapsule.timecapsule.exceptions.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;

import java.util.Objects;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = {AppException.class} )
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity.status(ex.getCode())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().getFirst().getDefaultMessage();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder().message(errorMessage).build());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<ErrorDto> handleHttpMessageNotReadableException(
            HttpMessageNotReadableException ex) {
        
        String errorMessage = "Invalid request format";

        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException invalidFormatEx) {
            String fieldName = getFieldName(invalidFormatEx);
            String invalidValue = invalidFormatEx.getValue().toString();
            Class<?> targetType = invalidFormatEx.getTargetType();
            
            if (targetType == java.time.LocalDate.class) {
                errorMessage = String.format("Invalid date format for field '%s': '%s'. Expected format: yyyy-MM-dd", 
                    fieldName, invalidValue);
            } else if (targetType == Boolean.class || targetType == boolean.class) {
                errorMessage = String.format("Invalid boolean value for field '%s': '%s'. Expected 'true' or 'false'", 
                    fieldName, invalidValue);
            } else {
                errorMessage = String.format("Invalid value for field '%s': '%s'", fieldName, invalidValue);
            }
        } else if (cause instanceof JsonMappingException) {
            errorMessage = "Invalid JSON format: " + cause.getMessage();
        }
        
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorDto.builder().message(errorMessage).build());
    }
    
    private String getFieldName(InvalidFormatException ex) {
        return ex.getPath().stream()
                .map(JsonMappingException.Reference::getFieldName)
                .filter(Objects::nonNull)
                .findFirst()
                .orElse("unknown");
    }
}