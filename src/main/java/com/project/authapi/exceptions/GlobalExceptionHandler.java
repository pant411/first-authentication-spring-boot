package com.project.authapi.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.project.authapi.exceptions.caseExceptions.NotFoundException;
import com.project.authapi.exceptions.caseExceptions.UnauthorizedException;
import com.project.authapi.exceptions.models.ErrorResponse;
import com.project.authapi.exceptions.models.ErrorResponseValidate;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ErrorResponse> notFoundException(NotFoundException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage());

    return new ResponseEntity<ErrorResponse>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ErrorResponse> unauthorizedException(UnauthorizedException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.UNAUTHORIZED.value(),
        ex.getMessage());
    return new ResponseEntity<ErrorResponse>(message, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<ErrorResponse> authenticationException(AuthenticationException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.UNAUTHORIZED.value(),
        ex.getMessage());

    return new ResponseEntity<ErrorResponse>(message, HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(UsernameNotFoundException.class)
  public ResponseEntity<ErrorResponse> usernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage());

    return new ResponseEntity<ErrorResponse>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseValidate> validateException(MethodArgumentNotValidException ex,
      WebRequest request) {
    List<String> fieldErrors = new ArrayList<>();
    ex.getAllErrors().forEach(err -> fieldErrors.add(err.getDefaultMessage()));
    ErrorResponseValidate message = new ErrorResponseValidate(
        HttpStatus.BAD_REQUEST.value(),
        "Validation fail",
        fieldErrors);

    return new ResponseEntity<ErrorResponseValidate>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex, WebRequest request) {
    ErrorResponse message = new ErrorResponse(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getMessage());

    return new ResponseEntity<ErrorResponse>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  // Add more exception handlers as needed
}
