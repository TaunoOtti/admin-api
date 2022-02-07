package com.demo.app.application.service.loan.dto;

import com.demo.app.application.mapper.RequestMapperUtil;
import com.demo.app.domain.loan.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {
        RequestMapperUtil.class
})
public interface LoanDtoMapper {
    LoanDto toDto(Loan loan);

    @Mapping(target = "loanId", ignore = true)
    @Mapping(target = "createdDtime", ignore = true)
    @Mapping(target = "modifiedDtime", ignore = true)
    Loan toLoan(LoanRequestDto dto, Long customerId);
}
