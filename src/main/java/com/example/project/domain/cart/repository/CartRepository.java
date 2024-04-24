package com.example.project.domain.cart.repository;

import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.product.model.Product;
import com.example.project.domain.user.model.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CartRepository extends JpaRepository<Cart, Long> {

  boolean existsByUserAndProduct(User user, Product product);

  @Query("select c from Cart c join fetch c.product where c.user.userId = :userId")
  List<Cart> findAllByUserId(Long userId);
}
