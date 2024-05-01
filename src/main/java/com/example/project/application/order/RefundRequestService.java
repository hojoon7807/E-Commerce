package com.example.project.application.order;

import com.example.project.application.order.usecase.RefundRequestCommand;
import com.example.project.application.order.usecase.RefundRequestUseCase;
import com.example.project.domain.order.exception.OrderNotFoundException;
import com.example.project.domain.order.model.Order;
import com.example.project.domain.order.repository.OrderRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RefundRequestService implements RefundRequestUseCase {

  private final OrderRepository orderRepository;

  @Override
  @Transactional
  public Order apply(RefundRequestCommand command) {
    Order order = findOrder(command.orderNum());
    order.validateUser(command.userId());
    order.refundRequest();
    return order;
  }

  private Order findOrder(UUID orderNum) {
    return orderRepository.findByOrderNum(orderNum).orElseThrow(OrderNotFoundException::new);
  }
}
