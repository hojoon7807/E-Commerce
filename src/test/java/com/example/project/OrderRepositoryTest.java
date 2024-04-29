package com.example.project;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.project.domain.order.model.Order;
import com.example.project.domain.order.model.OrderStatus;
import com.example.project.domain.order.repository.OrderRepository;
import com.example.project.domain.user.model.User;
import com.example.project.infrastructure.security.TwoWayEncryptorAdapter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

@DataJpaTest
@ActiveProfiles("test")
@Import({JpaTestConfig.class, TwoWayEncryptorAdapter.class})
class OrderRepositoryTest {

  @Autowired
  OrderRepository orderRepository;

  @Test
  @Sql("order-test.sql")
  void saveTest() {
    Order order = Order.builder()
                       .user(User.builder()
                                 .userId(1L)
                                 .build())
                       .orderStatus(OrderStatus.RESERVATION_ORDER)
                       .receiverName("홍길동")
                       .receiverPhoneNum("010-1234-5678")
                       .receiverZipcode("12345")
                       .receiverAddressMain("서울시 강남구")
                       .receiverAddressSub("역삼동")
                       .totalPrice(10000)
                       .build();

    Order savedOrder = orderRepository.save(order);

    assertThat(savedOrder.getOrderNum()).isNotNull();
  }
}
