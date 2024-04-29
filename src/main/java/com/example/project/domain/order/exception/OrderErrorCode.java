package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {

  ORDER_PRODUCT_OVER_QUANTITY("OP001", "수량은 최대 1000 입니다.", 400),
  ORDER_OUT_OF_STOCK("OP002", "재고가 부족합니다.", 400),
  ORDER_CART_INVALID("OP003", "카트가 유효하지 않습니다.", 400);

  private final String code;
  private final String message;
  private final int status;

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public int getStatus() {
    return this.status;
  }
}
