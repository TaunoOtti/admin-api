package ee.backend.api.application;

import ee.backend.api.application.RestControllerExceptionHandler;
import ee.backend.api.application.error.ErrorCode;
import ee.backend.api.application.error.ErrorDto;
import ee.backend.api.domain.BusinessValidationException;
import ee.backend.api.domain.ObjectNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

import static ee.backend.api.application.RestControllerExceptionHandler.INVALID_INPUT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RestControllerExceptionHandlerTest {

    private final RestControllerExceptionHandler restControllerExceptionHandler = new RestControllerExceptionHandler();

    @Test
    void shouldHandleRuntimeException() {
        var response = restControllerExceptionHandler.handleException(new RuntimeException("message"));
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), response.getStatus());
        assertEquals(ErrorCode.TECHNICAL_ERROR.name(), response.getCode());
        assertEquals("message", response.getMessage());
        assertNull(response.getFields());
    }

    @Test
    void shouldHandleObjectNotFoundException() {
        var response = restControllerExceptionHandler.handleException(new ObjectNotFoundException("message"));
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatus());
        assertEquals(ErrorCode.NOT_FOUND.name(), response.getCode());
        assertEquals("message", response.getMessage());
        assertNull(response.getFields());
    }

    @Test
    void shouldHandleMethodArgumentTypeMismatchException() {
        var response = restControllerExceptionHandler.handleException(
                new MethodArgumentTypeMismatchException(
                        LocalDateTime.now(), String.class, "name", null, new NumberFormatException()));
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals(ErrorCode.VALIDATION_ERROR.name(), response.getCode());
        assertEquals(INVALID_INPUT, response.getMessage());
        assertNull(response.getFields());
    }

    @Test
    void shouldHandleBusinessValidationException() {
        var response = restControllerExceptionHandler.handleException(
                new BusinessValidationException("new message"));
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals(ErrorCode.VALIDATION_ERROR.name(), response.getCode());
        assertEquals("new message", response.getMessage());
        assertNull(response.getFields());
    }

    @Test
    void shouldHandleMethodArgumentNotValid() {
        BindingResult bindingResult = new BeanPropertyBindingResult("target", "ObjectName");
        bindingResult.addError(new FieldError(
                "objectName",
                "firstName",
                " ",
                true,
                null,
                null,
                "default message")
        );
        var exception = new MethodArgumentNotValidException(null, bindingResult);

        var responseEntity = restControllerExceptionHandler.handleMethodArgumentNotValid(
                exception, new HttpHeaders(), HttpStatus.BAD_REQUEST, null);

        ErrorDto response = (ErrorDto) responseEntity.getBody();
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
        assertEquals(ErrorCode.VALIDATION_ERROR.name(), response.getCode());
        assertEquals(INVALID_INPUT, response.getMessage());
        assertEquals(1, response.getFields().size());
        assertEquals("firstName", response.getFields().get(0).getName());
        assertEquals("default message", response.getFields().get(0).getMessage());
    }
}