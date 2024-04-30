package com.example.project.presentation.payment.dto.request;

import com.example.project.application.payment.usecase.ApprovePaymentCommand;
import com.example.project.infrastructure.UUIDUtil;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record ApprovePaymentRequest(
    @NotBlank
    String paymentKey,
    @NotBlank
    String orderNum,
    @Min(0)
    int paymentAmount
) {

  public ApprovePaymentCommand toCommand(Long userId) {
    return new ApprovePaymentCommand(userId, paymentKey, UUIDUtil.decodeFromShort(orderNum),
        paymentAmount);
  }

}
