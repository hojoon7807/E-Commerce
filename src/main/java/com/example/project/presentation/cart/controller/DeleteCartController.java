package com.example.project.presentation.cart.controller;

import com.example.project.application.cart.usecase.DeleteCartCommand;
import com.example.project.application.cart.usecase.DeleteCartUseCase;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class DeleteCartController {

  private final DeleteCartUseCase deleteCartUseCase;

  @DeleteMapping("/user/carts/{cartId}")
  public ResponseEntity<Object> deleteCart(
      @PathVariable @Min(1) Long cartId,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    deleteCartUseCase.accept(new DeleteCartCommand(userId, cartId));
    return ResponseEntity.ok().build();
  }
}
