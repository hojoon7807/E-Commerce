package com.example.project.application.order.usecase;

import com.example.project.domain.order.model.Order;
import java.util.function.Function;

public interface RefundRequestUseCase extends Function<RefundRequestCommand, Order> {

}
