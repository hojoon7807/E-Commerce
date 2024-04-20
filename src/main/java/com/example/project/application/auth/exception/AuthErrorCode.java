package com.example.project.application.auth.exception;

import com.example.project.presentation.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {

  LOGIN_FAIL("A001", "아이디나 비밀번호가 잘못되었습니다."),
  EMAIL_VERIFICATION_NOT_COMPLETED("A002", "이메일 인증이 완료되지 않았습니다.");

  private final String code;
  private final String message;

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }
}
