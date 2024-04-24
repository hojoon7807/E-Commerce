package com.example.project.domain.cart.exception;

import com.example.project.presentation.common.error.BusinessException;

public class CartUserMismatchException extends BusinessException {

  public CartUserMismatchException(){
    super(CartErrorCode.CART_USER_MISMATCH);
  }
}
