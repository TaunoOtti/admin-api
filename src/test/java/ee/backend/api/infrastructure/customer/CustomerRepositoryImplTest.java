package ee.backend.api.infrastructure.customer;

import ee.backend.api.domain.customer.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryImplTest {

    @InjectMocks
    private CustomerRepositoryImpl customerRepository;

    @Mock
    private CustomerJpaRepository customerJpaRepository;

    @Mock
    private CustomerEntityMapper customerEntityMapper;

    @Test
    void shouldGetAllCustomers() {
        when(customerJpaRepository.findAll()).thenReturn(List.of(new CustomerEntity()));
        customerRepository.getAllCustomers();
        verify(customerJpaRepository).findAll();
        verify(customerEntityMapper).toCustomer(any(CustomerEntity.class));
    }

    @Test
    void getCustomer() {
        when(customerJpaRepository.findById(1L)).thenReturn(Optional.of(new CustomerEntity()));
        customerRepository.getCustomer(1L);
        verify(customerJpaRepository).findById(1L);
        verify(customerEntityMapper).toCustomer(any(CustomerEntity.class));
    }

    @Test
    void saveCustomer() {
        when(customerEntityMapper.toEntity(any(Customer.class))).thenReturn(new CustomerEntity());
        when(customerJpaRepository.save(any(CustomerEntity.class))).thenReturn(new CustomerEntity());
        customerRepository.saveCustomer(new Customer());
        verify(customerEntityMapper).toEntity(any(Customer.class));
        verify(customerJpaRepository).save(any(CustomerEntity.class));
        verify(customerEntityMapper).toCustomer(any(CustomerEntity.class));
    }

    @Test
    void deleteCustomerById() {
        customerRepository.deleteCustomerById(1L);
        verify(customerJpaRepository).deleteById(1L);
    }
}