package com.example.project.application.cart;

import com.example.project.application.cart.usecase.AddCartCommand;
import com.example.project.application.cart.usecase.AddCartUseCase;
import com.example.project.domain.cart.exception.DuplicatedCartException;
import com.example.project.domain.product.exception.ProductNotFoundException;
import com.example.project.domain.user.exception.UserNotFoundException;
import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.cart.repository.CartRepository;
import com.example.project.domain.product.model.Product;
import com.example.project.domain.product.repository.ProductRepository;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AddCartService implements AddCartUseCase {

  private final UserRepository userRepository;
  private final ProductRepository productRepository;
  private final CartRepository cartRepository;

  @Override
  public void accept(AddCartCommand command) {
    User user = findUser(command.userId());
    Product product = findProduct(command.productId());

    if (cartRepository.existsByUserAndProduct(user, product)) {
      throw new DuplicatedCartException();
    }

    Cart cart = Cart.builder()
                     .user(user)
                     .product(product)
                     .productQuantity(command.productQuantity())
                     .build();

    cartRepository.save(cart);
  }

  private User findUser(Long userId) {
    return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
  }

  private Product findProduct(Long productId) {
    return productRepository.findById(productId).orElseThrow(ProductNotFoundException::new);
  }
}
