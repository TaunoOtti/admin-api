package com.demo.app.infrastructure.loan;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LoanJpaRepository extends CrudRepository<LoanEntity, Long> {

    @Override
    List<LoanEntity> findAll();

    List<LoanEntity> findAllByCustomerId(Long customerId);
}
