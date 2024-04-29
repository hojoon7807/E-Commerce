package com.example.project.application.order;

import com.example.project.application.order.usecase.CreateOrderCommand;
import com.example.project.application.order.usecase.CreateOrderUseCase;
import com.example.project.domain.cart.model.Cart;
import com.example.project.domain.cart.repository.CartRepository;
import com.example.project.domain.order.exception.OrderCartInvalidException;
import com.example.project.domain.order.exception.OrderOutOfStockException;
import com.example.project.domain.order.model.Order;
import com.example.project.domain.order.model.OrderProduct;
import com.example.project.domain.order.model.OrderStatus;
import com.example.project.domain.order.repository.OrderProductRepository;
import com.example.project.domain.order.repository.OrderRepository;
import com.example.project.domain.product.model.Product;
import com.example.project.domain.user.model.Address;
import com.example.project.domain.user.model.User;
import com.example.project.domain.user.repository.AddressRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {

  private final OrderRepository orderRepository;
  private final OrderProductRepository orderProductRepository;
  private final CartRepository cartRepository;
  private final AddressRepository addressRepository;

  @Override
  @Transactional
  public List<OrderProduct> apply(CreateOrderCommand command) {
    List<Cart> carts = cartRepository.findAllInCartIds(command.cartIds());

    verifyCarts(carts, command.userId(), command.cartIds());

    Address addressWithUser = addressRepository.findDefaultAddressWithUserByUserId(
        command.userId());

    User orderer = addressWithUser.getUser();

    Order newOrder = Order.builder()
                          .user(orderer)
                          .totalPrice(calculatePrice(carts))
                          .orderStatus(OrderStatus.TEMPORARY_ORDER)
                          .receiverName(orderer.getUsername())
                          .receiverPhoneNum(orderer.getPhoneNum())
                          .receiverZipcode(addressWithUser.getZipcode())
                          .receiverAddressMain(addressWithUser.getAddressMain())
                          .receiverAddressSub(addressWithUser.getAddressSub())
                          .build();

    Order savedOrder = orderRepository.save(newOrder);

    List<OrderProduct> orderProducts = createOrderProducts(savedOrder, carts);

    return orderProductRepository.saveAll(orderProducts);
  }

  private List<OrderProduct> createOrderProducts(Order order, List<Cart> carts) {
    return carts.stream()
                .map(cart -> {
                  Product product = cart.getProduct();

                  if (!product.hasStock(cart.getProductQuantity())) {
                    throw new OrderOutOfStockException();
                  }

                  product.decreaseStock(cart.getProductQuantity());

                  return OrderProduct.builder()
                                     .order(order)
                                     .product(product)
                                     .orderQuantity(cart.getProductQuantity())
                                     .orderPrice(product.getPrice())
                                     .build();
                }).toList();
  }

  private int calculatePrice(List<Cart> carts) {
    return carts.stream()
                .mapToInt(cart -> cart.getProduct().getPrice() * cart.getProductQuantity())
                .sum();
  }

  private void verifyCarts(List<Cart> carts, Long userId, List<Long> cartIds) {
    if (carts.size() != cartIds.size()) {
      throw new OrderCartInvalidException();
    }

    carts.forEach(cart -> cart.validateUser(userId));
  }

}
