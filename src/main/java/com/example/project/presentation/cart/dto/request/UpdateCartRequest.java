package com.example.project.presentation.cart.dto.request;

import com.example.project.application.cart.usecase.UpdateCartCommand;
import jakarta.validation.constraints.Min;

public record UpdateCartRequest(
    @Min(1)
    int productQuantity
) {

  public UpdateCartCommand toCommand(Long userId, Long cartId){
    return new UpdateCartCommand(userId, cartId, productQuantity);
  }
}
