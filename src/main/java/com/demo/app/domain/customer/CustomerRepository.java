package com.demo.app.domain.customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getAllCustomers();

    Customer getCustomer(Long customerId);

    Customer saveCustomer(Customer customer);

    void deleteCustomerById(Long customerId);
}
