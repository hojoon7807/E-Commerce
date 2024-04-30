package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.BusinessException;

public class OrderNotFoundException extends BusinessException {

  public OrderNotFoundException() {
    super(OrderErrorCode.ORDER_NOT_FOUND);
  }
}
