package ee.backend.api.infrastructure.customer;

import ee.backend.api.domain.CustomerTestUtil;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CustomerEntityMapperTest {
    
    private final CustomerEntityMapper customerEntityMapper = new CustomerEntityMapperImpl();

    @Test
    void shouldMapDomainCustomerToEntity() {
        var customer = CustomerTestUtil.createCustomer();
        var entity = customerEntityMapper.toEntity(customer);

        assertEquals(customer.getCustomerId(), entity.getCustomerId());
        assertEquals(customer.getFirstName(), entity.getFirstName());
        assertEquals(customer.getLastName(), entity.getLastName());
        assertEquals(customer.getAddress(), entity.getAddress());
        assertEquals(customer.getEmail(), entity.getEmail());
        assertEquals(customer.getDateOfBirth(), entity.getDateOfBirth());
        assertEquals(customer.getPhoneNo(), entity.getPhoneNo());
        assertEquals(customer.getCreatedDtime(), entity.getCreatedDtime());
        assertEquals(customer.getModifiedDtime(), entity.getModifiedDtime());
    }

    @Test
    void shouldMapEntityToDomainCustomer() {
        var entity = createEntity();
        var customer = customerEntityMapper.toCustomer(entity);

        assertEquals(entity.getCustomerId(), customer.getCustomerId());
        assertEquals(entity.getFirstName(), customer.getFirstName());
        assertEquals(entity.getLastName(), customer.getLastName());
        assertEquals(entity.getAddress(), customer.getAddress());
        assertEquals(entity.getEmail(), customer.getEmail());
        assertEquals(entity.getDateOfBirth(), customer.getDateOfBirth());
        assertEquals(entity.getPhoneNo(), customer.getPhoneNo());
        assertEquals(entity.getCreatedDtime(), customer.getCreatedDtime());
        assertEquals(entity.getModifiedDtime(), customer.getModifiedDtime());
    }

    private CustomerEntity createEntity() {
        CustomerEntity entity = new CustomerEntity();
        entity.setCustomerId(2L);
        entity.setFirstName("firstname1");
        entity.setLastName("lastname1");
        entity.setAddress("address1");
        entity.setEmail("email1@email.com");
        entity.setPhoneNo("33322122");
        entity.setDateOfBirth(LocalDate.of(1990, 1, 1));
        entity.setCreatedDtime(LocalDateTime.now());
        entity.setModifiedDtime(LocalDateTime.now());
        return entity;
    }
}