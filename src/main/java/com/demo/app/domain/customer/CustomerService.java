package com.demo.app.domain.customer;

import com.demo.app.domain.ObjectNotFoundException;
import com.demo.app.domain.BusinessValidationException;
import com.demo.app.domain.loan.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final LoanService loanService;

    public List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public Customer getCustomer(Long customerId) {
        var customer = customerRepository.getCustomer(customerId);
        if (customer == null) {
            throwCustomerNotFound(customerId);
        }
        return customer;
    }

    public void checkCustomerExists(Long customerId) {
        boolean exists = customerRepository.customerExists(customerId);
        if (!exists) {
            throwCustomerNotFound(customerId);
        }
    }

    public static void throwCustomerNotFound(Long customerId) {
        throw new ObjectNotFoundException(String.format("Customer with id: %d not found", customerId));
    }

    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepository.saveCustomer(customer);
    }

    @Transactional
    public void removeCustomer(Long customerId) {
        checkCustomerExists(customerId);
        boolean activeLoans = loanService.customerHasAnyActiveLoan(customerId);
        if (activeLoans) {
            throw new BusinessValidationException("Deletion failed - customer has active loans");
        } else {
            customerRepository.deleteCustomerById(customerId);
        }
    }
}
