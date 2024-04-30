package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.BusinessException;

public class OrderUserMismatchException extends BusinessException {

  public OrderUserMismatchException() {
    super(OrderErrorCode.ORDER_USER_MISMATCH);
  }
}
