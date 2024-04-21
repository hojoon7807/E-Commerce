package com.example.project.infrastructure.security;

import com.example.project.application.common.TwoWayEncryptor;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.encrypt.BytesEncryptor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TwoWayEncryptorAdapter implements TwoWayEncryptor {

  private final BytesEncryptor bytesEncryptor;

  @Override
  public String encrypt(String rawText) {
    byte[] bytes = rawText.getBytes();
    byte[] encryptedBytes = bytesEncryptor.encrypt(bytes);

    return Base64.getEncoder().encodeToString(encryptedBytes);
  }

  @Override
  public String decrypt(String cipherText) {
    byte[] bytes = Base64.getDecoder().decode(cipherText);
    byte[] decryptedBytes = bytesEncryptor.decrypt(bytes);

    return new String(decryptedBytes);
  }
}
