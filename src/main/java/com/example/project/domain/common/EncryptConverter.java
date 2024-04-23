package com.example.project.domain.common;

import com.example.project.application.common.TwoWayEncryptor;
import jakarta.persistence.AttributeConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EncryptConverter implements AttributeConverter<String, String> {

  private final TwoWayEncryptor twoWayEncryptor;

  @Override
  public String convertToDatabaseColumn(String attribute) {
    if (attribute == null) {
      return null;
    }
    return twoWayEncryptor.encrypt(attribute);
  }

  @Override
  public String convertToEntityAttribute(String dbData) {
    if (dbData == null) {
      return null;
    }
    return twoWayEncryptor.decrypt(dbData);
  }
}
