package com.demo.app.application.service.loan.dto;

import com.demo.app.application.service.dto.AuditDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class LoanDto extends AuditDto {
    private Long loanId;
    private String loanApplicationName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate startDate;
    private Integer loanPeriodInMonths;
    private BigDecimal amount;
    private Long customerId;
}
