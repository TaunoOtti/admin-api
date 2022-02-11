package ee.backend.api.application.service;

import com.fasterxml.jackson.core.type.TypeReference;
import ee.backend.api.application.IntegrationTest;
import ee.backend.api.application.service.customer.dto.CustomerDto;
import ee.backend.api.domain.customer.Customer;
import ee.backend.api.domain.customer.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends IntegrationTest {

    @Autowired
    private CustomerService customerService;

    @Test
    void shouldGetAllCustomers() throws Exception {
        var customer1 = customerService.saveCustomer(createCustomer());
        var customer2 = customerService.saveCustomer(createCustomer());

        var result = mockMvc.perform(MockMvcRequestBuilders.get("/api/customers")
                        .contentType("application/json"))
                        .andExpect(status().isOk())
                        .andReturn();
        var responseList = objectMapper.readValue(
                result.getResponse().getContentAsString(),
                new TypeReference<List<CustomerDto>>(){});

        assertThat(responseList.size(), greaterThanOrEqualTo(2));
        assertNotNull(responseList.stream().filter(c -> c.getCustomerId() == customer1.getCustomerId()).findFirst());
        assertNotNull(responseList.stream().filter(c -> c.getCustomerId() == customer2.getCustomerId()).findFirst());
    }

    // TODO TESTS

    private Customer createCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("First");
        customer.setLastName("Last");
        customer.setAddress("address");
        customer.setEmail("email@email.com");
        customer.setPhoneNo("+3721234567");
        customer.setDateOfBirth(LocalDate.of(1995, 6, 15));
        return customer;
    }
}
