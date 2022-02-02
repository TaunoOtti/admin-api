package com.demo.app.infrastructure;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "com.demo.app.infrastructure")
@EntityScan(basePackages = "com.demo.app.infrastructure")
@EnableTransactionManagement
public class JpaConfig {
}
