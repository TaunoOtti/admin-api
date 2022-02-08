package com.demo.app.domain.customer;

import com.demo.app.domain.BusinessValidationException;
import com.demo.app.domain.ObjectNotFoundException;
import com.demo.app.domain.loan.LoanService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LoanService loanService;

    @Test
    void shouldGetAllCustomer() {
        var customer = List.of(new Customer());
        when(customerRepository.getAllCustomers()).thenReturn(customer);

        var list = customerService.getAllCustomers();

        verify(customerRepository).getAllCustomers();
        assertEquals(customer, list);
    }

    @Test
    void shouldGetCustomer() {
        var newCustomer = new Customer();
        when(customerRepository.getCustomer(1L)).thenReturn(newCustomer);

        var customer = customerService.getCustomer(1L);

        verify(customerRepository).getCustomer(1L);
        assertEquals(newCustomer, customer);
    }

    @Test
    void shouldGetCustomer_ThrowsObjectNotFound() {
        when(customerRepository.getCustomer(1L)).thenReturn(null);

        var exception =
                assertThrows(ObjectNotFoundException.class, () -> customerService.getCustomer(1L));

        assertEquals("Customer with id: 1 not found", exception.getMessage());
    }

    @Test
    void shouldSaveCustomer() {
        var savedCustomer = new Customer();
        when(customerRepository.saveCustomer(any(Customer.class))).thenReturn(savedCustomer);
        var modifiedCustomer = new Customer();

        var response = customerService.saveCustomer(modifiedCustomer);

        verify(customerRepository).saveCustomer(modifiedCustomer);
        assertEquals(savedCustomer, response);
    }

    @Test
    void shouldRemoveCustomerById_NoActiveLoans() {
        when(customerRepository.customerExists(1L)).thenReturn(true);
        when(loanService.customerHasAnyActiveLoan(1L)).thenReturn(false);
        customerService.removeCustomer(1L);

        verify(customerRepository).deleteCustomerById(1L);
    }

    @Test
    void shouldThrowExceptionWhenRemovingCustomer_CustomerNotFound() {
        when(customerRepository.customerExists((1L))).thenReturn(false);
        assertThrows(ObjectNotFoundException.class, () -> customerService.removeCustomer(1L));

        verify(customerRepository, times(0)).deleteCustomerById(1L);
    }

    @Test
    void shouldThrowExceptionWhenRemovingCustomer_CustomerHasActiveLoans() {
        when(loanService.customerHasAnyActiveLoan(1L)).thenReturn(true);
        when(customerRepository.customerExists(1L)).thenReturn(true);
        assertThrows(BusinessValidationException.class, () -> customerService.removeCustomer(1L));
        verify(customerRepository, times(0)).deleteCustomerById(1L);
    }
}