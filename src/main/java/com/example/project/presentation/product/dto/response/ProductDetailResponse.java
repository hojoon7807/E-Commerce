package com.example.project.presentation.product.dto.response;

import com.example.project.domain.product.model.Product;
import com.example.project.domain.product.model.ProductStatus;
import java.time.LocalDateTime;

public record ProductDetailResponse(
    Long productId,
    String productName,
    String productContent,
    ProductStatus productStatus,
    int price,
    int stock,
    LocalDateTime createdAt,
    LocalDateTime modifiedAt,
    Long sellerId,
    String sellerName
) {

  public static ProductDetailResponse of(Product product) {
    return new ProductDetailResponse(
        product.getProductId(), product.getProductName(), product.getProductContent(),
        product.getProductStatus(), product.getPrice(), product.getStock(),
        product.getCreatedAt(), product.getModifiedAt(),
        product.getUser().getUserId(), product.getUser().getUsername());
  }
}
