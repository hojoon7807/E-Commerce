package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.BusinessException;

public class CanNotCancelOrderException extends BusinessException {

  public CanNotCancelOrderException() {
    super(OrderErrorCode.CAN_NOT_CANCEL_ORDER);
  }

}
