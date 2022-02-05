package com.demo.app.infrastructure.customer;

import com.demo.app.domain.customer.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    CustomerEntity toEntity(Customer customer);
    Customer toCustomer(CustomerEntity entity);
}
