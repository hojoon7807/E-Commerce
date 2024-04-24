package com.example.project.application.cart;

import com.example.project.application.cart.usecase.DeleteCartCommand;
import com.example.project.application.cart.usecase.DeleteCartUseCase;
import com.example.project.domain.cart.exception.CartNotFoundException;
import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.cart.repository.CartRepository;
import com.example.project.domain.user.exception.UserNotFoundException;
import com.example.project.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteCartService implements DeleteCartUseCase {

  private final UserRepository userRepository;
  private final CartRepository cartRepository;

  @Override
  @Transactional
  public void accept(DeleteCartCommand command) {
    if (!userRepository.existsByUserId(command.userId())) {
      throw new UserNotFoundException();
    }

    Cart cart = findCart(command.cartId());
    cart.validateUser(command.userId());

    cartRepository.delete(cart);
  }

  private Cart findCart(Long cartId) {
    return cartRepository.findById(cartId).orElseThrow(CartNotFoundException::new);
  }
}
