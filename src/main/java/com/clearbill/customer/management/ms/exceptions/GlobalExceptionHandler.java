package com.clearbill.customer.management.ms.exceptions;

import com.clearbill.customer.management.ms.dto.error.ErrorDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.Collections;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ErrorDTO errorDTO;

    @ExceptionHandler(JsonProcessingException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(JsonProcessingException exception , HttpServletRequest request){
        LOGGER.error("Json processing exception error due to incorrect payload "+exception.getMessage());

        LOGGER.error(Arrays.toString(exception.getStackTrace()));

        errorDTO.includeErrorType("Bad Request").includeError(Collections.singletonList(exception.getMessage())).includePath(request.getRequestURI()).includeResolution(Collections.singletonList("Your request payload contains few invalid fields which could not be processed!! Kindly remove them and try again!!!"));
        LOGGER.error(errorDTO.toString());

        return new ResponseEntity<>(errorDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(ValidationException exception , HttpServletRequest request){
        LOGGER.error("Validation error :"+ exception.getMessage());

        LOGGER.error(Arrays.toString(exception.getStackTrace()));

        errorDTO.includeErrorType("Validation failed").includeError(exception.getErrorList()).includePath(request.getRequestURI()).includeResolution(exception.getResolutionList());
        LOGGER.error(errorDTO.toString());

        return new ResponseEntity<>(errorDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<ErrorDTO> exceptionHandler(CustomerNotFoundException exception , HttpServletRequest request){
        LOGGER.error("Validation error :"+ exception.getMessage());

        LOGGER.error(Arrays.toString(exception.getStackTrace()));

        errorDTO.includeErrorType("Not Found").includePath(request.getRequestURI()).includeError(Collections.singletonList(exception.getMessage()));

        LOGGER.error(errorDTO.toString());

        return new ResponseEntity<>(errorDTO, HttpStatus.NOT_FOUND);
    }
}
