package com.demo.app.application.user.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDate;
    private String phone;
    private String address;
    private LocalDateTime createdDtime;
    private LocalDateTime modifiedDtime;
}
