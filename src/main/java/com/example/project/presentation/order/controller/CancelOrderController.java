package com.example.project.presentation.order.controller;

import com.example.project.application.order.usecase.CancelOrderCommand;
import com.example.project.application.order.usecase.CancelOrderUseCase;
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
public class CancelOrderController {

  private final CancelOrderUseCase cancelOrderUseCase;

  @PatchMapping("/user/orders/{orderNum}/cancel")
  public ResponseEntity<Object> cancelOrder(
      @PathVariable String orderNum,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    cancelOrderUseCase.apply(new CancelOrderCommand(userId, UUIDUtil.decodeFromShort(orderNum)));
    return ResponseEntity.ok().build();
  }
}
