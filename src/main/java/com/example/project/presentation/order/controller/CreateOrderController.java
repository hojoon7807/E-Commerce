package com.example.project.presentation.order.controller;

import com.example.project.application.order.usecase.CreateOrderUseCase;
import com.example.project.domain.order.model.OrderProduct;
import com.example.project.presentation.order.dto.request.CreateOrderRequest;
import com.example.project.presentation.order.dto.response.CreateOrderResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CreateOrderController {

  private final CreateOrderUseCase createOrderUseCase;

  @PostMapping("/user/orders")
  public ResponseEntity<CreateOrderResponse> createOrder(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody @Valid CreateOrderRequest createOrderRequest) {
    Long userId = Long.valueOf(userDetails.getUsername());

    List<OrderProduct> orderProducts = createOrderUseCase.apply(
        createOrderRequest.toCommand(userId));

    return ResponseEntity.ok(CreateOrderResponse.of(orderProducts));
  }
}
