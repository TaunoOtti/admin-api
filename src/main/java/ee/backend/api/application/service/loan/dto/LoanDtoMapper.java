package ee.backend.api.application.service.loan.dto;

import ee.backend.api.application.mapper.RequestMapperUtil;
import ee.backend.api.domain.loan.Loan;
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
