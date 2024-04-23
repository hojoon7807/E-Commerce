package com.example.project.application.user.exception;

import com.example.project.presentation.common.error.BusinessException;

public class AddressNotFoundException extends BusinessException {

  public AddressNotFoundException() {
    super(UserErrorCode.ADDRESS_NOT_FOUND);
  }
}
