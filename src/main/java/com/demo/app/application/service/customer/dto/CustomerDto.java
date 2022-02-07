package com.demo.app.application.service.customer.dto;

import com.demo.app.application.service.dto.AuditDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomerDto extends AuditDto {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate dateOfBirth;
    private String phoneNo;
    private String address;
}
