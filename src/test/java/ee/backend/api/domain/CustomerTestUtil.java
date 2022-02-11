package ee.backend.api.domain;

import ee.backend.api.domain.customer.Customer;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CustomerTestUtil {

    public static Customer createCustomer() {
        return Customer.builder()
                .customerId(1L)
                .firstName("firstname")
                .lastName("lastname")
                .address("address")
                .email("as@asd.ee")
                .phoneNo("12345678")
                .dateOfBirth(LocalDate.of(1995, 6, 15))
                .createdDtime(LocalDateTime.now())
                .modifiedDtime(LocalDateTime.now())
                .build();
    }
}
