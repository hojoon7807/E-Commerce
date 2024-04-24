package com.example.project.application.cart;

import com.example.project.application.cart.usecase.UpdateCartCommand;
import com.example.project.application.cart.usecase.UpdateCartUseCase;
import com.example.project.domain.cart.exception.CartNotFoundException;
import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCartService implements UpdateCartUseCase {

  private final CartRepository cartRepository;

  @Override
  @Transactional
  public Cart apply(UpdateCartCommand command) {
    Cart cart = findCart(command.cartId());

    cart.validateUser(command.userId());
    cart.changeQuantity(command.productQuantity());

    return cart;
  }

  public Cart findCart(Long cartId) {
    return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
  }
}
