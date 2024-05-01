package com.example.project.presentation.order.controller;

import com.example.project.application.order.usecase.RefundRequestCommand;
import com.example.project.application.order.usecase.RefundRequestUseCase;
import com.example.project.infrastructure.UUIDUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RefundRequestController {

  private final RefundRequestUseCase refundRequestUseCase;

  @PatchMapping("/user/orders/{orderNum}/refund")
  public ResponseEntity<Object> refundRequest(
      @PathVariable String orderNum,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    refundRequestUseCase.apply(
        new RefundRequestCommand(userId, UUIDUtil.decodeFromShort(orderNum)));
    return ResponseEntity.ok().build();
  }
}
