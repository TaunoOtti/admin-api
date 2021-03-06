package ee.backend.api.application.service.customer;

import ee.backend.api.application.service.customer.dto.CustomerDto;
import ee.backend.api.application.service.customer.dto.CustomerRequestDto;
import ee.backend.api.domain.customer.Customer;
import ee.backend.api.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final static String CUSTOMER_API_PATH = "/api/customers";
    private final CustomerService customerService;
    private final CustomerDtoMapper customerDtoMapper;

    @GetMapping(value = CUSTOMER_API_PATH)
    public List<CustomerDto> getCustomers() {
        return customerService.getAllCustomers().stream()
                .map(customerDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = CUSTOMER_API_PATH + "/{customerId}")
    public CustomerDto getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);
        return customerDtoMapper.toDto(customer);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = CUSTOMER_API_PATH)
    public CustomerDto createCustomer(@Valid @RequestBody CustomerRequestDto customerDto) {
        Customer customer = customerDtoMapper.toCustomer(customerDto);
        Customer createdCustomer = customerService.saveCustomer(customer);
        return customerDtoMapper.toDto(createdCustomer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = CUSTOMER_API_PATH + "/{customerId}")
    public void updateCustomer(@PathVariable Long customerId, @Valid @RequestBody CustomerRequestDto customerDto) {
        Customer originalCustomer = customerService.getCustomer(customerId);
        customerDtoMapper.mapFieldsToExistingCustomer(originalCustomer, customerDto);
        customerService.saveCustomer(originalCustomer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = CUSTOMER_API_PATH + "/{customerId}")
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.removeCustomer(customerId);
    }
}
