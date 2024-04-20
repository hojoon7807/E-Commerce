package com.example.project.application.auth.exception;

import com.example.project.presentation.common.error.BusinessException;

public class LoginFailException extends BusinessException {

  public LoginFailException() {
    super(AuthErrorCode.LOGIN_FAIL);
  }
}
