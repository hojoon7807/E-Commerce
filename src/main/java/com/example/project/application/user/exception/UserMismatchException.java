package com.example.project.application.user.exception;

import com.example.project.presentation.common.error.BusinessException;

public class UserMismatchException extends BusinessException {
  public UserMismatchException() {
    super(UserErrorCode.USER_MISMATCH);
  }
}
