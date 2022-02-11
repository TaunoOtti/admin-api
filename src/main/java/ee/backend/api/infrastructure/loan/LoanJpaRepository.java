package ee.backend.api.infrastructure.loan;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface LoanJpaRepository extends CrudRepository<LoanEntity, Long> {

    @Override
    List<LoanEntity> findAll();

    List<LoanEntity> findAllByCustomerId(Long customerId);

    Optional<LoanEntity> findByLoanIdAndCustomerId(Long loanId, Long customerId);
}
