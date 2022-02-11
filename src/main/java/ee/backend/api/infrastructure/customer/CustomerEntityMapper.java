package ee.backend.api.infrastructure.customer;

import ee.backend.api.domain.customer.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerEntityMapper {
    CustomerEntity toEntity(Customer customer);
    Customer toCustomer(CustomerEntity entity);
}
