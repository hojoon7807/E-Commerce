package com.example.project.application.cart.usecase;

import com.example.project.domain.cart.model.Cart;
import java.util.function.Function;

public interface UpdateCartUseCase extends Function<UpdateCartCommand, Cart> {

}
