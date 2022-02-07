package com.demo.app.domain.loan;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Loan {
    private Long loanId;
    private String loanApplicationName;
    private LocalDate startDate;
    private Integer loanPeriodInMonths;
    private BigDecimal amount;
    private Long customerId;
    private LocalDateTime createdDtime;
    private LocalDateTime modifiedDtime;
}
