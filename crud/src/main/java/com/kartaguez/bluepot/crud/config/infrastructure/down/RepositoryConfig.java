package com.kartaguez.bluepot.crud.config.infrastructure.down;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(com.kartaguez.bluepot.infrastructure.down.repository.jpa.RepositoryJpaConfig.class)
public class RepositoryConfig {
}
