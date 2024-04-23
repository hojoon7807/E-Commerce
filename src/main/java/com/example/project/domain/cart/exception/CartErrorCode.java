package com.example.project.domain.cart.exception;

import com.example.project.presentation.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum CartErrorCode implements ErrorCode {

  CART_OVER_QUANTITY("CT001", "수량은 최대 1000 입니다.", 400),
  DUPLICATED_CART("CT002", "이미 해당 장바구니가 존재합니다.", 400);

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
