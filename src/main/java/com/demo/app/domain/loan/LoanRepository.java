package com.demo.app.domain.loan;

import java.util.List;

public interface LoanRepository {
    List<Loan> getAllLoans();
    List<Loan> getCustomerLoans(Long customerId);
    Loan save(Loan loan);
}
