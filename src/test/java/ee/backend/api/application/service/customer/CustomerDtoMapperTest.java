package ee.backend.api.application.service.customer;

import ee.backend.api.application.mapper.RequestMapperUtil;
import ee.backend.api.application.service.customer.dto.CustomerDto;
import ee.backend.api.application.service.customer.dto.CustomerRequestDto;
import ee.backend.api.domain.customer.Customer;
import ee.backend.api.domain.CustomerTestUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
class CustomerDtoMapperTest {

    @InjectMocks
    private CustomerDtoMapperImpl customerDtoMapper;

    @Spy
    private RequestMapperUtil requestMapperUtil;

    @Test
    void shouldMapCustomertoCustomerDto() {
        Customer customer = CustomerTestUtil.createCustomer();
        CustomerDto dto = customerDtoMapper.toDto(customer);
        assertEquals(customer.getCustomerId(), dto.getCustomerId());
        assertEquals(customer.getFirstName(), dto.getFirstName());
        assertEquals(customer.getLastName(), dto.getLastName());
        assertEquals(customer.getAddress(), dto.getAddress());
        assertEquals(customer.getEmail(), dto.getEmail());
        assertEquals(customer.getDateOfBirth(), dto.getDateOfBirth());
        assertEquals(customer.getCreatedDtime(), dto.getCreatedDtime());
        assertEquals(customer.getModifiedDtime(), dto.getModifiedDtime());
    }

    @Test
    void shouldMapFieldsToExistingCustomer() {
        Customer customer = CustomerTestUtil.createCustomer();
        CustomerRequestDto dto = createCustomerRequest();
        customerDtoMapper.mapFieldsToExistingCustomer(customer, dto);
        assertEquals(dto.getFirstName(), dto.getFirstName());
        assertEquals(dto.getLastName(), dto.getLastName());
        assertEquals(dto.getAddress(), dto.getAddress());
        assertEquals(dto.getEmail(), dto.getEmail());
        assertEquals(dto.getDateOfBirth(), dto.getDateOfBirth());
        assertEquals(1L, customer.getCustomerId());
        assertNotNull(customer.getCreatedDtime());
        assertNotNull(customer.getModifiedDtime());

    }

    @Test
    void shouldMapCustomerDtoToCustomer() {
        CustomerRequestDto dto = createCustomerRequest();
        Customer customer = customerDtoMapper.toCustomer(dto);
        assertEquals(dto.getFirstName(), dto.getFirstName());
        assertEquals(dto.getLastName(), dto.getLastName());
        assertEquals(dto.getAddress(), dto.getAddress());
        assertEquals(dto.getEmail(), dto.getEmail());
        assertEquals(dto.getDateOfBirth(), dto.getDateOfBirth());
        assertNull(customer.getCustomerId());
        assertNull(customer.getCreatedDtime());
        assertNull(customer.getModifiedDtime());
    }

    private CustomerRequestDto createCustomerRequest() {
        CustomerRequestDto request = new CustomerRequestDto();
        request.setFirstName("firstname1");
        request.setLastName("lastname1");
        request.setAddress("address1");
        request.setEmail("email1@email.com");
        request.setPhoneNo("33322122");
        request.setDateOfBirth(LocalDate.of(1990, 1, 1));
        return request;
    }
}