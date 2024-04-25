package com.example.project.presentation.product.controller;

import com.example.project.application.product.usecase.ProductDetailUseCase;
import com.example.project.domain.product.model.Product;
import com.example.project.presentation.product.dto.response.ProductDetailResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductDetailController {

  private final ProductDetailUseCase productDetailUseCase;

  @GetMapping("/products/{productId}")
  public ResponseEntity<ProductDetailResponse> productDetail(@PathVariable @Min(1) Long productId) {
    Product product = productDetailUseCase.apply(productId);
    return ResponseEntity.ok(ProductDetailResponse.of(product));
  }
}
