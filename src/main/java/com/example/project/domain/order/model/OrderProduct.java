package com.example.project.domain.order.model;

import com.example.project.domain.common.model.BaseTime;
import com.example.project.domain.order.exception.OrderProductOverQuantityException;
import com.example.project.domain.product.model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_product")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class OrderProduct extends BaseTime {

  private static final int MAX_ORDER_QUANTITY = 1000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderProductId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id", nullable = false)
  private Order order;

  @Column(nullable = false, columnDefinition = "smallint unsigned")
  private int orderQuantity;

  @Column(nullable = false, columnDefinition = "int unsigned")
  private int orderPrice;

  public static class OrderProductBuilder {

    public OrderProductBuilder orderQuantity(int orderQuantity) {
      if (orderQuantity > MAX_ORDER_QUANTITY) {
        throw new OrderProductOverQuantityException();
      }
      this.orderQuantity = orderQuantity;
      return this;
    }
  }
}
