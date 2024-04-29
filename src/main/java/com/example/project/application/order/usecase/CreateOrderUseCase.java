package com.example.project.application.order.usecase;

import com.example.project.domain.order.model.OrderProduct;
import java.util.List;
import java.util.function.Function;

public interface CreateOrderUseCase extends Function<CreateOrderCommand, List<OrderProduct>> {

}
