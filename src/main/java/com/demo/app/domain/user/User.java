package com.demo.app.domain.user;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Builder
@Data
public class User {
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
