package com.example.project.domain.product.repository;

import com.example.project.domain.product.model.Product;
import com.example.project.domain.product.model.ProductStatus;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {

  @Query("select p from Product p join fetch p.user where p.productId = :productId")
  Optional<Product> findByProductId(Long productId);

  Page<Product> findAllByProductStatus(ProductStatus productStatus, Pageable pageable);
}
