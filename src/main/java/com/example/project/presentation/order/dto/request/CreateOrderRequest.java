package com.example.project.presentation.order.dto.request;

import com.example.project.application.order.usecase.CreateOrderCommand;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record CreateOrderRequest(
    @NotEmpty
    List<@Min(1) Long> cartIds
) {

  public CreateOrderCommand toCommand(Long userId) {
    return new CreateOrderCommand(userId, cartIds);
  }
}
