package com.example.project.domain.cart.exception;

import com.example.project.presentation.common.error.BusinessException;

public class CartNotFoundException extends BusinessException {

  public CartNotFoundException() {
    super(CartErrorCode.CART_NOT_FOUND);
  }
}
