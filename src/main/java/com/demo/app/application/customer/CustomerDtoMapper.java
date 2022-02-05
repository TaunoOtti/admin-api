package com.demo.app.application.customer;

import com.demo.app.application.mapper.RequestMapperUtil;
import com.demo.app.application.customer.dto.CustomerDto;
import com.demo.app.application.customer.dto.CustomerRequestDto;
import com.demo.app.domain.customer.Customer;
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
