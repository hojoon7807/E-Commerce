package com.example.project.application.product;

import com.example.project.application.product.usecase.GetProductsUseCase;
import com.example.project.domain.product.model.Product;
import com.example.project.domain.product.model.ProductStatus;
import com.example.project.domain.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetProductService implements GetProductsUseCase {

  private final ProductRepository productRepository;

  @Override
  @Transactional(readOnly = true)
  public Page<Product> apply(Pageable pageable) {
    return productRepository.findAllByProductStatus(ProductStatus.ON_SALE, pageable);
  }
}
