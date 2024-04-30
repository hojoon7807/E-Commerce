package com.example.project.presentation.payment.controller;

import com.example.project.application.payment.usecase.ApprovePaymentUseCase;
import com.example.project.domain.payment.Payment;
import com.example.project.presentation.payment.dto.request.ApprovePaymentRequest;
import com.example.project.presentation.payment.dto.response.ApprovePaymentResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ApprovePaymentController {

  private final ApprovePaymentUseCase approvePaymentUseCase;

  @PostMapping("/payments")
  public ResponseEntity<Object> approvePayment(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody @Valid ApprovePaymentRequest approvePaymentRequest
  ) {
    Long userId = Long.valueOf(userDetails.getUsername());
    Payment payment = approvePaymentUseCase.apply(approvePaymentRequest.toCommand(userId));
    return ResponseEntity.ok(ApprovePaymentResponse.of(payment));
  }
}
