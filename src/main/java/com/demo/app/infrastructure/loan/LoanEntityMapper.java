package com.demo.app.infrastructure.loan;

import com.demo.app.domain.loan.Loan;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface LoanEntityMapper {
    LoanEntity toEntity(Loan loan);
    Loan toLoan(LoanEntity entity);
}
