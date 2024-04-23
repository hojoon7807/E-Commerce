package com.example.project.domain.cart.exception;

import com.example.project.presentation.common.error.BusinessException;

public class CartOverQuantityException extends BusinessException {

  public CartOverQuantityException() {
    super(CartErrorCode.CART_OVER_QUANTITY);
  }
}
