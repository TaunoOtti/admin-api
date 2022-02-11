package ee.backend.api.application.service.customer.dto;

import ee.backend.api.application.service.dto.AuditDto;
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
