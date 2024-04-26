package com.example.project.presentation.product.controller;

import com.example.project.application.product.usecase.GetProductsUseCase;
import com.example.project.domain.product.model.Product;
import com.example.project.presentation.product.dto.response.GetProductsResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class GetProductsController {

  private final GetProductsUseCase getProductsUseCase;

  @GetMapping("/products")
  public ResponseEntity<GetProductsResponse> getProducts(
      @PageableDefault(size = 20, sort = "productId", direction = Direction.DESC) Pageable pageable) {
    Page<Product> page = getProductsUseCase.apply(pageable);

    return ResponseEntity.ok(GetProductsResponse.of(page));
  }
}
