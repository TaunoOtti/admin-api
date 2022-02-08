package com.demo.app.application;

import com.demo.app.application.error.ErrorCode;
import com.demo.app.application.error.ErrorDto;
import com.demo.app.application.error.ErrorFieldDto;
import com.demo.app.domain.BusinessValidationException;
import com.demo.app.domain.ObjectNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RestControllerExceptionHandler extends ResponseEntityExceptionHandler {
    protected static final String INVALID_INPUT = "Invalid input";

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDto handleException(ObjectNotFoundException exception) {
        return ErrorDto.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .code(ErrorCode.NOT_FOUND.name())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorDto handleException(RuntimeException exception) {
        return ErrorDto.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(ErrorCode.TECHNICAL_ERROR.name())
                .message(exception.getMessage())
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(MethodArgumentTypeMismatchException exception) {
        return ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ErrorCode.VALIDATION_ERROR.name())
                .message(INVALID_INPUT)
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleException(BusinessValidationException exception) {
        return ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ErrorCode.VALIDATION_ERROR.name())
                .message(exception.getMessage())
                .build();
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ErrorCode.VALIDATION_ERROR.name())
                .message(INVALID_INPUT)
                .build();

        ex.getBindingResult().getFieldErrors()
                .forEach((FieldError fieldError) ->
                    errorDto.addField(ErrorFieldDto.builder()
                            .name(fieldError.getField())
                            .message(fieldError.getDefaultMessage())
                            .build())
                );

        return new ResponseEntity<>(errorDto, headers, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        var errorDto = ErrorDto.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(ErrorCode.VALIDATION_ERROR.name())
                .message(INVALID_INPUT)
                .build();

        return new ResponseEntity<>(errorDto, headers, HttpStatus.BAD_REQUEST);
    }
}
