package com.demo.app.application.user.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class UserRequestDto {
    @NotBlank
    @Size(min = 1, max = 255)
    private String firstName;
    @NotBlank
    @Size(min = 1, max = 255)
    private String lastName;
    @Email
    private String email;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;
    @NotBlank
    @Size(min = 1, max = 50)
    private String phone;
    @NotBlank
    @Size(min = 1, max = 255)
    private String address;
}
