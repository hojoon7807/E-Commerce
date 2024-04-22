package com.example.project.application.user.exception;

import com.example.project.presentation.common.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {

  DUPLICATED_EMAIL("U001", "중복된 이메일입니다."),
  USER_NOT_FOUND("U002", "존재하지 않는 회원입니다."),
  PASSWORD_MISMATCH("U003", "비밀번호가 일치하지 않습니다.");

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
