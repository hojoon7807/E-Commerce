package com.example.project.domain.product.model;

import com.example.project.domain.common.model.BaseTime;
import com.example.project.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long productId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  private String productName;

  @Column(nullable = false, columnDefinition = "text")
  private String productContent;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @Enumerated(EnumType.STRING)
  private ProductStatus productStatus;

  @Column(nullable = false, columnDefinition = "int unsigned")
  private int price;

  @Column(nullable = false, columnDefinition = "int unsigned")
  private int stock;

  public boolean hasStock(int orderQuantity) {
    return stock >= orderQuantity;
  }

  public void decreaseStock(int orderQuantity) {
    this.stock -= orderQuantity;
  }
  @Builder
  public Product(Long productId, User user, String productName, String productContent,
      ProductStatus productStatus, int price, int stock) {
    this.productId = productId;
    this.user = user;
    this.productName = productName;
    this.productContent = productContent;
    this.productStatus = productStatus;
    this.price = price;
    this.stock = stock;
  }
}
