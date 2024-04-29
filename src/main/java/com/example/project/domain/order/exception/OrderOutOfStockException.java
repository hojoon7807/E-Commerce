package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.BusinessException;

public class OrderOutOfStockException extends BusinessException {

  public OrderOutOfStockException(){
    super(OrderErrorCode.ORDER_OUT_OF_STOCK);
  }
}
