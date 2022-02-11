package ee.backend.api.domain.loan;

import java.util.List;

public interface LoanRepository {
    List<Loan> getAllLoans();
    List<Loan> getCustomerLoans(Long customerId);
    Loan save(Loan loan);
    Loan getLoanByIdAndCustomerId(Long loanId, Long customerId);
    void delete(Long loanId);
}
