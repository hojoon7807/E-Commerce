package com.example.project.presentation.cart.dto.response;

import com.example.project.domain.cart.model.Cart;

public record UpdateCartResponse(
    Long cartId,
    int productQuantity
) {

  public static UpdateCartResponse of(Cart cart) {
    return new UpdateCartResponse(cart.getCartId(), cart.getProductQuantity());
  }
}
