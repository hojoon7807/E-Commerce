package com.example.project.application.user.usecase;

public interface OneWayEncryptor {

  String encode(CharSequence rawPassword);

  boolean match(CharSequence rawPassword, String encodedPassword);
}
