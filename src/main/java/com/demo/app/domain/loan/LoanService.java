package com.demo.app.domain.loan;

import com.demo.app.domain.customer.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;
    private final CustomerService customerService;

    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }

    public List<Loan> getAllCustomerLoans(Long customerId) {
        return loanRepository.getCustomerLoans(customerId);
    }

    @Transactional
    public Loan createLoan(Loan loan) {
        customerService.getCustomer(loan.getCustomerId());
        return loanRepository.save(loan);
    }
}
