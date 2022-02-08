package com.demo.app.domain.loan;

import com.demo.app.domain.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LoanService {
    private final LoanRepository loanRepository;

    public List<Loan> getAllLoans() {
        return loanRepository.getAllLoans();
    }

    public Loan getLoanByIdAndCustomerId(Long loanId, Long customerId) {
        return loanRepository.getLoanByIdAndCustomerId(loanId, customerId);
    }

    public List<Loan> getAllCustomerLoans(Long customerId) {
        return loanRepository.getCustomerLoans(customerId);
    }

    public boolean customerHasAnyActiveLoan(Long customerId) {
        return getAllCustomerLoans(customerId).stream()
                .anyMatch(loan ->
                    loan.getStartDate().plusMonths(loan.getLoanPeriodInMonths()).isAfter(LocalDate.now())
                );
    }

    @Transactional
    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    @Transactional
    public void deleteCustomerLoan(Long customerId, Long loanId) {
        var loan = getLoanByIdAndCustomerId(loanId, customerId);
        if (loan == null) {
            throw new ObjectNotFoundException(String.format("Customer:%d Loan: %d not found", customerId, loanId));
        }
        loanRepository.delete(loan.getLoanId());
    }
}
