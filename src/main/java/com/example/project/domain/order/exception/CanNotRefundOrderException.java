package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.BusinessException;

public class CanNotRefundOrderException extends BusinessException {

  public CanNotRefundOrderException() {
    super(OrderErrorCode.CAN_NOT_REFUND_ORDER);
  }
}
