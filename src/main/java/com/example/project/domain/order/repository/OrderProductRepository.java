package com.example.project.domain.order.repository;

import com.example.project.domain.order.model.OrderProduct;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Long> {

  @Query("select op from OrderProduct op where op.order.orderId = :orderId")
  List<OrderProduct> findAllByOrderId(Long orderId);

  @Query("select op from OrderProduct op join fetch op.product where op.order.orderId = :orderId")
  List<OrderProduct> findAllWithProductByOrderId(Long orderId);
}
