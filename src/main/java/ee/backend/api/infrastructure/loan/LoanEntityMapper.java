package ee.backend.api.infrastructure.loan;

import ee.backend.api.domain.loan.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanEntityMapper {
    LoanEntity toEntity(Loan loan);
    Loan toLoan(LoanEntity entity);
}
