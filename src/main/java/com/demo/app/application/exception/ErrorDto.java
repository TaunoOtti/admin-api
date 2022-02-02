package com.demo.app.application.exception;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorDto {
    private Integer status;
    private String code;
    private String message;
}
