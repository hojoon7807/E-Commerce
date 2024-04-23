package com.example.project.domain.user.exception;

import com.example.project.presentation.common.error.BusinessException;

public class PasswordMismatchException extends BusinessException {

  public PasswordMismatchException() {
    super(UserErrorCode.PASSWORD_MISMATCH);
  }
}
