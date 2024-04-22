package com.example.project.application.user.exception;

import com.example.project.presentation.common.error.BusinessException;

public class PasswordMismatchException extends BusinessException {

  public PasswordMismatchException() {
    super(UserErrorCode.PASSWORD_MISMATCH);
  }
}
