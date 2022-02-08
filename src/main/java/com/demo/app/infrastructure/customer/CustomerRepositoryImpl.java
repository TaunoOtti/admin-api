package com.demo.app.infrastructure.customer;

import com.demo.app.domain.customer.Customer;
import com.demo.app.domain.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {
    private final CustomerJpaRepository customerJpaRepository;
    private final CustomerEntityMapper customerEntityMapper;

    @Override
    public List<Customer> getAllCustomers() {
        return customerJpaRepository.findAll().stream()
                .map(customerEntityMapper::toCustomer)
                .collect(Collectors.toList());
    }

    @Override
    public Customer getCustomer(Long customerId) {
        return customerJpaRepository.findById(customerId)
                .map(customerEntityMapper::toCustomer)
                .orElse(null);
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        var entity = customerEntityMapper.toEntity(customer);
        var saved = customerJpaRepository.save(entity);
        return customerEntityMapper.toCustomer(saved);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerJpaRepository.deleteById(customerId);
    }

    @Override
    public boolean customerExists(Long customerId) {
        return customerJpaRepository.existsById(customerId);
    }
}
