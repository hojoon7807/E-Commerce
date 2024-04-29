package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.BusinessException;

public class OrderProductOverQuantityException extends BusinessException {

  public OrderProductOverQuantityException(){
    super(OrderErrorCode.ORDER_PRODUCT_OVER_QUANTITY);
  }
}
