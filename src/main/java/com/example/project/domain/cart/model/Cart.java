package com.example.project.domain.cart.model;

import com.example.project.domain.cart.exception.CartOverQuantityException;
import com.example.project.domain.cart.exception.CartUserMismatchException;
import com.example.project.domain.common.model.BaseTime;
import com.example.project.domain.product.model.Product;
import com.example.project.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User.UserBuilder;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Cart extends BaseTime {

  private static final int MAX_PRODUCT_QUANTITY = 1000;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long cartId;

  @Column(nullable = false, columnDefinition = "smallint unsigned")
  private int productQuantity;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "product_id")
  private Product product;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  public void validateUser(Long userId){
    if(!this.user.getUserId().equals(userId)){
      throw new CartUserMismatchException();
    }
  }

  @Builder
  public Cart(Long cartId, int productQuantity, Product product, User user) {
    this.cartId = cartId;
    this.productQuantity = productQuantity;
    this.product = product;
    this.user = user;
  }

  public static class CartBuilder {

    public CartBuilder productQuantity(int productQuantity) {
      if (productQuantity > MAX_PRODUCT_QUANTITY) {
        throw new CartOverQuantityException();
      }
      this.productQuantity = productQuantity;
      return this;
    }
  }
}
