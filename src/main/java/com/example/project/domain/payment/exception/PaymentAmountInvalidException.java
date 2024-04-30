package com.example.project.domain.payment.exception;

import com.example.project.presentation.common.error.BusinessException;

public class PaymentAmountInvalidException extends BusinessException {

  public PaymentAmountInvalidException() {
    super(PaymentErrorCode.PAYMENT_AMOUNT_INVALID);
  }

}
