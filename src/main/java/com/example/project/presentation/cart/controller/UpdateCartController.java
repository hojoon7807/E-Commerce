package com.example.project.presentation.cart.controller;

import com.example.project.application.cart.usecase.UpdateCartUseCase;
import com.example.project.domain.cart.model.Cart;
import com.example.project.presentation.cart.dto.request.UpdateCartRequest;
import com.example.project.presentation.cart.dto.response.UpdateCartResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateCartController {

  private final UpdateCartUseCase updateCartUseCase;

  @PatchMapping("/user/carts/{cartId}")
  public ResponseEntity<UpdateCartResponse> updateCart(
      @PathVariable @Min(1) Long cartId,
      @RequestBody @Valid UpdateCartRequest updateCartRequest,
      @AuthenticationPrincipal UserDetails userDetails
  ) {
    Long userId = Long.valueOf(userDetails.getUsername());
    Cart cart = updateCartUseCase.apply(updateCartRequest.toCommand(userId, cartId));
    return ResponseEntity.ok(UpdateCartResponse.of(cart));
  }
}
