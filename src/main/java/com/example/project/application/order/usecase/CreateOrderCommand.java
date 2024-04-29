package com.example.project.application.order.usecase;

import java.util.List;

public record CreateOrderCommand(
    Long userId,
    List<Long> cartIds
) {

}
