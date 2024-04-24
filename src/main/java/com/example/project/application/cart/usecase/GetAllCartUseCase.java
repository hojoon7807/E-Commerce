package com.example.project.application.cart.usecase;

import com.example.project.domain.cart.model.Cart;
import java.util.List;
import java.util.function.Function;

public interface GetAllCartUseCase extends Function<Long, List<Cart>> {

}
