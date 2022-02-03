package com.demo.app.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
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
