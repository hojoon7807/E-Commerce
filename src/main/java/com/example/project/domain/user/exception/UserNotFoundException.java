package com.example.project.domain.user.exception;

import com.example.project.presentation.common.error.BusinessException;

public class UserNotFoundException extends BusinessException {

  public UserNotFoundException() {
    super(UserErrorCode.USER_NOT_FOUND);
  }

}
