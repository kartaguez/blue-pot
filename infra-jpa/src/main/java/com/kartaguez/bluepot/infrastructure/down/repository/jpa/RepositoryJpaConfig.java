package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped")
@EntityScan(basePackages = "com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity")
@ComponentScan(basePackages = "com.kartaguez.bluepot.infrastructure.down.repository.jpa")
public class RepositoryJpaConfig {

}
