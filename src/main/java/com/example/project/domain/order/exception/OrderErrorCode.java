package com.example.project.domain.order.exception;

import com.example.project.presentation.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum OrderErrorCode implements ErrorCode {

  ORDER_PRODUCT_OVER_QUANTITY("OP001", "수량은 최대 1000 입니다.", 400),
  ORDER_OUT_OF_STOCK("OP002", "재고가 부족합니다.", 400),
  ORDER_CART_INVALID("OP003", "카트가 유효하지 않습니다.", 400),
  ORDER_NOT_FOUND("OP004", "주문을 찾을 수 없습니다.", 400),
  ORDER_USER_MISMATCH ("OP005", "주문자가 다릅니다.", 400),
  CAN_NOT_CANCEL_ORDER("OP006", "주문을 취소할 수 없습니다.", 400),
  CAN_NOT_REFUND_ORDER("OP007", "주문을 반품할 수 없습니다.", 400);

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
