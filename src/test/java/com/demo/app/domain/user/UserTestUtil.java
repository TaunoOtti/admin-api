package com.demo.app.domain.user;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UserTestUtil {

    public static User createUser() {
        return User.builder()
                .userId(1L)
                .firstName("firstname")
                .lastName("lastname")
                .address("address")
                .email("as@asd.ee")
                .phone("12345678")
                .birthDate(LocalDate.of(1995, 6, 15))
                .createdDtime(LocalDateTime.now())
                .modifiedDtime(LocalDateTime.now())
                .build();
    }
}
