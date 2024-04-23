package com.example.project.domain.cart.repository;

import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.product.model.Product;
import com.example.project.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

  boolean existsByUserAndProduct(User user, Product product);
}
