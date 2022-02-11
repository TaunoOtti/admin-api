package ee.backend.api.application.service.loan.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class LoanRequestDto {
    @NotBlank
    @Size(min = 1, max = 255)
    private String loanApplicationName;
    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate startDate;
    @NotNull
    private Integer loanPeriodInMonths;
    @NotNull
    private BigDecimal amount;
}
