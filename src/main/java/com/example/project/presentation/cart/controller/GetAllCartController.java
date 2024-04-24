package com.example.project.presentation.cart.controller;

import com.example.project.application.cart.usecase.GetAllCartUseCase;
import com.example.project.domain.cart.model.Cart;
import com.example.project.presentation.cart.dto.response.GetAllCartResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetAllCartController {

  private final GetAllCartUseCase getAllCartUseCase;

  @GetMapping("/user/carts")
  public ResponseEntity<List<GetAllCartResponse>> getAllCart(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    List<Cart> carts = getAllCartUseCase.apply(userId);
    List<GetAllCartResponse> response = carts.stream().map(GetAllCartResponse::of).toList();

    return ResponseEntity.ok(response);
  }
}
