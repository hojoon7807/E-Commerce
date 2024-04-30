package com.example.project.application.payment.usecase;

import java.util.UUID;

public record ApprovePaymentCommand(
    Long userId,
    String paymentKey,
    UUID orderNum,
    int paymentAmount
) {

}
