package com.example.project.application.auth.exception;

import com.example.project.presentation.common.error.BusinessException;

public class EmailVerificationNotCompletedException extends BusinessException {

  public EmailVerificationNotCompletedException() {
    super(AuthErrorCode.EMAIL_VERIFICATION_NOT_COMPLETED);
  }
}
