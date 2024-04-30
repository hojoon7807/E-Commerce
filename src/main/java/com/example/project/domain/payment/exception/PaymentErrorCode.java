package com.example.project.domain.payment.exception;

import com.example.project.presentation.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum PaymentErrorCode implements ErrorCode {

  PAYMENT_AMOUNT_INVALID("P001", "결제 금액이 유효하지 않습니다.", 400);

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
