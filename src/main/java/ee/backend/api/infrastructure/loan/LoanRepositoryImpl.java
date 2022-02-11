package ee.backend.api.infrastructure.loan;

import ee.backend.api.domain.loan.Loan;
import ee.backend.api.domain.loan.LoanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class LoanRepositoryImpl implements LoanRepository {
    private final LoanJpaRepository loanJpaRepository;
    private final LoanEntityMapper loanEntityMapper;

    @Override
    public List<Loan> getAllLoans() {
        return loanJpaRepository.findAll().stream()
                .map(loanEntityMapper::toLoan)
                .collect(Collectors.toList());
    }

    @Override
    public List<Loan> getCustomerLoans(Long customerId) {
        return loanJpaRepository.findAllByCustomerId(customerId).stream()
                .map(loanEntityMapper::toLoan)
                .collect(Collectors.toList());
    }

    @Override
    public Loan save(Loan loan) {
        var entity = loanEntityMapper.toEntity(loan);
        var saved = loanJpaRepository.save(entity);
        return loanEntityMapper.toLoan(saved);
    }

    @Override
    public Loan getLoanByIdAndCustomerId(Long loanId, Long customerId) {
        return loanJpaRepository.findByLoanIdAndCustomerId(loanId, customerId)
                .map(loanEntityMapper::toLoan)
                .orElse(null);
    }

    @Override
    public void delete(Long loanId) {
        loanJpaRepository.deleteById(loanId);
    }
}
