package com.demo.app.infrastructure.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserJpaRepository extends CrudRepository<UserEntity, Long> {

    @Override
    List<UserEntity> findAll();

}
