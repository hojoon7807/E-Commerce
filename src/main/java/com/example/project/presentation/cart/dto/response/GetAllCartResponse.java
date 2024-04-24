package com.example.project.presentation.cart.dto.response;

import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.product.model.ProductStatus;

public record GetAllCartResponse(
    Long cartId,
    Long productId,
    String productName,
    ProductStatus productStatus,
    int productQuantity,
    int price
) {

  public static GetAllCartResponse of(Cart cart) {
    return new GetAllCartResponse(
        cart.getCartId(),
        cart.getProduct().getProductId(),
        cart.getProduct().getProductName(),
        cart.getProduct().getProductStatus(),
        cart.getProductQuantity(),
        cart.getProduct().getPrice());
  }
}
