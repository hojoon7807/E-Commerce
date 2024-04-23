package com.example.project.presentation.cart.controller;

import com.example.project.application.cart.usecase.AddCartUseCase;
import com.example.project.presentation.cart.dto.request.AddCartRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddCartController {

  private final AddCartUseCase addCartUseCase;

  @PostMapping("/user/carts")
  public ResponseEntity<Object> addCart(
      @RequestBody @Valid AddCartRequest addCartRequest,
      @AuthenticationPrincipal UserDetails userDetails
  ) {
    Long userId = Long.valueOf(userDetails.getUsername());
    addCartUseCase.accept(addCartRequest.toCommand(userId));
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
