package com.example.project.application.common;

public interface TwoWayEncryptor {

  String encrypt(String rawText);

  String decrypt(String cipherText);

}
