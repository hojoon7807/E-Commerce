package com.example.project.presentation.cart.dto.request;

import com.example.project.application.cart.usecase.AddCartCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record AddCartRequest(
    @Min(1)
    @NotNull
    Long productId,

    @Min(1)
    int productQuantity
) {

  public AddCartCommand toCommand(Long userId) {
    return new AddCartCommand(userId, productId, productQuantity);
  }
}
