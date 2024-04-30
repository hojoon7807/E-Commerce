package com.example.project.application.order;

import com.example.project.application.order.usecase.CancelOrderCommand;
import com.example.project.application.order.usecase.CancelOrderUseCase;
import com.example.project.domain.order.exception.OrderNotFoundException;
import com.example.project.domain.order.model.Order;
import com.example.project.domain.order.model.OrderProduct;
import com.example.project.domain.order.repository.OrderProductRepository;
import com.example.project.domain.order.repository.OrderRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CancelOrderService implements CancelOrderUseCase {

  private final OrderRepository orderRepository;
  private final OrderProductRepository orderProductRepository;

  @Override
  @Transactional
  public Order apply(CancelOrderCommand command) {
    Order order = findOrder(command.orderNum());

    order.validateUser(command.userId());
    order.cancelOrder();

    // TODO 결제 취소 요청

    // 재고 복구
    List<OrderProduct> orderProducts = orderProductRepository
        .findAllWithProductByOrderId(order.getOrderId());

    orderProducts.forEach(
        orderProduct -> orderProduct.getProduct().restoreStock(orderProduct.getOrderQuantity())
    );

    return order;
  }

  private Order findOrder(UUID orderNum) {
    return orderRepository.findByOrderNum(orderNum).orElseThrow(OrderNotFoundException::new);
  }
}
