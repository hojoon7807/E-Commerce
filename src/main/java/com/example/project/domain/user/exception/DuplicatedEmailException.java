package com.example.project.domain.user.exception;

import com.example.project.presentation.common.error.BusinessException;

public class DuplicatedEmailException extends BusinessException {

  public DuplicatedEmailException() {
    super(UserErrorCode.DUPLICATED_EMAIL);
  }
}
