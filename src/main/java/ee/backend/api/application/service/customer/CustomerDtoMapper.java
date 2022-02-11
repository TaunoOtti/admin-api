package ee.backend.api.application.service.customer;

import ee.backend.api.application.mapper.RequestMapperUtil;
import ee.backend.api.application.service.customer.dto.CustomerDto;
import ee.backend.api.application.service.customer.dto.CustomerRequestDto;
import ee.backend.api.domain.customer.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {
        RequestMapperUtil.class
})
public interface CustomerDtoMapper {

    CustomerDto toDto(Customer customer);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdDtime", ignore = true)
    @Mapping(target = "modifiedDtime", ignore = true)
    Customer toCustomer(CustomerRequestDto dto);

    @Mapping(target = "customerId", ignore = true)
    @Mapping(target = "createdDtime", ignore = true)
    @Mapping(target = "modifiedDtime", ignore = true)
    void mapFieldsToExistingCustomer(@MappingTarget Customer originalCustomer, CustomerRequestDto dto);

}
