package com.example.project.presentation.product.dto.response;

import com.example.project.domain.product.model.Product;
import com.example.project.domain.product.model.ProductStatus;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;

public record GetProductsResponse(
    long totalCount,
    int totalPage,
    int currentPage,
    List<ProductSimple> productSimples
) {

  public record ProductSimple(
      Long productId,
      String productName,
      ProductStatus productStatus,
      int price,
      LocalDateTime createdAt
  ) {

    private static ProductSimple of(Product product) {
      return new ProductSimple(
          product.getProductId(), product.getProductName(), product.getProductStatus(),
          product.getPrice(), product.getCreatedAt());
    }
  }

  public static GetProductsResponse of(Page<Product> page) {
    List<ProductSimple> simples = page.getContent().stream().map(ProductSimple::of).toList();
    return new GetProductsResponse(
        page.getTotalElements(),
        page.getTotalPages(),
        page.getNumber(),
        simples
    );
  }
}
