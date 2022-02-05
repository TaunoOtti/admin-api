package com.demo.app.domain.customer;

import com.demo.app.domain.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomer(Long customerId) {
        var customer = customerRepository.getCustomer(customerId);
        if (customer == null) {
            throw new ObjectNotFoundException(String.format("Customer with id: %d not found", customerId));
        }
        return customer;
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Transactional
    public Customer createCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Transactional
    public void removeCustomer(Long customerId) {
        getCustomer(customerId);
        customerRepository.deleteCustomerById(customerId);
    }
}
