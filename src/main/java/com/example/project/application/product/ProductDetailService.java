package com.example.project.application.product;

import com.example.project.application.product.usecase.ProductDetailUseCase;
import com.example.project.domain.product.exception.ProductNotFoundException;
import com.example.project.domain.product.model.Product;
import com.example.project.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductDetailService implements ProductDetailUseCase {

  private final ProductRepository productRepository;
  @Override
  public Product apply(Long productId) {
    return findProduct(productId);
  }

  private Product findProduct(Long productId) {
    return productRepository.findByProductId(productId).orElseThrow(ProductNotFoundException::new);
  }
}
