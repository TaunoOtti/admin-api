package ee.backend.api.infrastructure.customer;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerJpaRepository extends CrudRepository<CustomerEntity, Long> {

    @Override
    List<CustomerEntity> findAll();

}
