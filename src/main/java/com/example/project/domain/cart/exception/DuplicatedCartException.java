package com.example.project.domain.cart.exception;

import com.example.project.presentation.common.error.BusinessException;

public class DuplicatedCartException extends BusinessException {

  public DuplicatedCartException() {
    super(CartErrorCode.DUPLICATED_CART);
  }
}
