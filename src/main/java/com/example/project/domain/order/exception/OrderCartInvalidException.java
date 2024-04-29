package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.BusinessException;

public class OrderCartInvalidException extends BusinessException {

  public OrderCartInvalidException() {
    super(OrderErrorCode.ORDER_CART_INVALID);
  }
}
