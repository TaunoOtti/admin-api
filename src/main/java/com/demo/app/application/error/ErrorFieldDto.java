package com.demo.app.application.error;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorFieldDto {
    private String name;
    private String message;
}
