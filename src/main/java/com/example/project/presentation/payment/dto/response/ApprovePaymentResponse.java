package com.example.project.presentation.payment.dto.response;

import com.example.project.domain.payment.Payment;

public record ApprovePaymentResponse(
    int paymentAmount
) {

  public static ApprovePaymentResponse of(Payment payment) {
    return new ApprovePaymentResponse(payment.getPaymentAmount());
  }
}
