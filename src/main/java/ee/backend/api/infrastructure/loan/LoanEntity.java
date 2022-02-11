package ee.backend.api.infrastructure.loan;

import ee.backend.api.infrastructure.AuditFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "loan")
public class LoanEntity extends AuditFields {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long loanId;
    private String loanApplicationName;
    private LocalDate startDate;
    private Integer loanPeriodInMonths;
    private BigDecimal amount;
    private Long customerId;
}
