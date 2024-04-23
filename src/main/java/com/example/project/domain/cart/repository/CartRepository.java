package com.example.project.domain.cart.repository;

import com.example.project.domain.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {

}
