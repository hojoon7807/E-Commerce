package com.example.project;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.security.crypto.encrypt.Encryptors;

@Configuration
@EnableJpaAuditing
public class JpaTestConfig {

  @Bean
  public BytesEncryptor bytesEncryptor() {
    return Encryptors.stronger("ecommerce-password", "deadbeef");
  }
}
