package com.project.authapi.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.project.authapi.exceptions.caseExceptions.NotFoundException;
import com.project.authapi.exceptions.caseExceptions.UnauthorizedException;
import com.project.authapi.exceptions.models.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
  
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<ErrorResponse>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ErrorResponse> unauthorizedException(UnauthorizedException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.UNAUTHORIZED.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<ErrorResponse>(message, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        new Date(),
        ex.getMessage(),
        request.getDescription(false));

    return new ResponseEntity<ErrorResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Add more exception handlers as needed
}
