package com.demo.app.application.error;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ErrorDto {
    private Integer status;
    private String code;
    private String message;
    private List<ErrorFieldDto> fields;

    public void addField(ErrorFieldDto errorFieldDto) {
        if (fields == null) {
            fields = new ArrayList<>();
        }
        fields.add(errorFieldDto);
    }
}
