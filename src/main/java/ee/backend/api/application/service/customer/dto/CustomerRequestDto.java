package ee.backend.api.application.service.customer.dto;

import ee.backend.api.application.validator.ValidPhoneNo;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class CustomerRequestDto {
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
    private LocalDate dateOfBirth;
    @Size(min = 1, max = 50)
    @ValidPhoneNo
    private String phoneNo;
    @NotBlank
    @Size(min = 1, max = 255)
    private String address;
}
