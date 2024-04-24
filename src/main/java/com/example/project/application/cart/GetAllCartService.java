package com.example.project.application.cart;

import com.example.project.application.cart.usecase.GetAllCartUseCase;
import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.cart.repository.CartRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GetAllCartService implements GetAllCartUseCase {

  private final CartRepository cartRepository;

  @Override
  @Transactional(readOnly = true)
  public List<Cart> apply(Long userId) {
    return cartRepository.findAllByUserId(userId);
  }
}
