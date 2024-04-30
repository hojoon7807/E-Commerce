package com.example.project.domain.order.repository;

import com.example.project.domain.order.model.Order;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

  Optional<Order> findByOrderNum(UUID orderNum);
}
