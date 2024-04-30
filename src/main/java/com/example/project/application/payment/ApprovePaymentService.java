package com.example.project.application.payment;

import com.example.project.application.payment.usecase.ApprovePaymentCommand;
import com.example.project.application.payment.usecase.ApprovePaymentUseCase;
import com.example.project.domain.cart.repository.CartRepository;
import com.example.project.domain.order.exception.OrderNotFoundException;
import com.example.project.domain.order.model.Order;
import com.example.project.domain.order.model.OrderProduct;
import com.example.project.domain.order.repository.OrderProductRepository;
import com.example.project.domain.order.repository.OrderRepository;
import com.example.project.domain.payment.Payment;
import com.example.project.domain.payment.exception.PaymentAmountInvalidException;
import com.example.project.domain.payment.repository.PaymentRepository;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ApprovePaymentService implements ApprovePaymentUseCase {

  private final PaymentRepository paymentRepository;
  private final OrderRepository orderRepository;
  private final OrderProductRepository orderProductRepository;
  private final CartRepository cartRepository;

  @Override
  @Transactional
  public Payment apply(ApprovePaymentCommand command) {
    Order order = findOrder(command.orderNum());

    if (order.getTotalPrice() != command.paymentAmount()) {
      throw new PaymentAmountInvalidException();
    }

    // 결제 승인 요청 후 payment 생성
    Payment payment = Payment.builder()
                             .paymentAmount(command.paymentAmount())
                             .paymentKey(command.paymentKey())
                             .build();

    Payment savedPayment = paymentRepository.save(payment);
    order.updatePaymentComplete(savedPayment);

    //장바구니 삭제

    removeCarts(command.userId(), order.getOrderId());

    return savedPayment;
  }


  private Order findOrder(UUID orderNum) {
    return orderRepository.findAllByOrderNum(orderNum).orElseThrow(OrderNotFoundException::new);
  }

  private void removeCarts(Long userId, Long orderId) {
    List<OrderProduct> orderProducts = findOrderProducts(orderId);
    List<Long> productIds = orderProducts.stream()
                                         .map(orderProduct -> orderProduct.getProduct()
                                                                          .getProductId())
                                         .toList();

    cartRepository.deleteAllCompletedOrder(userId, productIds);
  }

  private List<OrderProduct> findOrderProducts(Long orderId) {
    return orderProductRepository.findAllByOrderId(orderId);
  }

}
