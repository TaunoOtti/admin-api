package com.demo.app.application.user;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class UserDto {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private Date birthDate;
    private String phone;
    private String address;
    private LocalDateTime createdDtime;
    private LocalDateTime modifiedDtime;
}
