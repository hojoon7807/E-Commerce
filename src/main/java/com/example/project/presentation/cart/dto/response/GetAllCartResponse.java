package com.example.project.presentation.cart.dto.response;

import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.product.model.ProductStatus;
import java.time.LocalDateTime;

public record GetAllCartResponse(
    Long cartId,
    Long productId,
    String productName,
    ProductStatus productStatus,
    int productQuantity,
    int price,
    LocalDateTime modifiedAt
) {

  public static GetAllCartResponse of(Cart cart) {
    return new GetAllCartResponse(
        cart.getCartId(),
        cart.getProduct().getProductId(),
        cart.getProduct().getProductName(),
        cart.getProduct().getProductStatus(),
        cart.getProductQuantity(),
        cart.getProduct().getPrice(),
        cart.getModifiedAt());
  }
}
