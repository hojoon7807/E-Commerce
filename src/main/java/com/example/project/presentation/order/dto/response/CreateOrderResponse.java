package com.example.project.presentation.order.dto.response;

import com.example.project.domain.order.model.Order;
import com.example.project.domain.order.model.OrderProduct;
import com.example.project.infrastructure.UUIDUtil;
import java.util.List;

public record CreateOrderResponse(
    String orderNum,
    int totalPrice,
    String receiverName,
    String receiverPhoneNum,
    String receiverZipCode,
    String receiverAddressMain,
    String receiverAddressSub,
    List<OrderProductResponse> orderProducts

) {

  public record OrderProductResponse(
      Long productId,
      String productName,
      int orderPrice,
      int orderQuantity) {

  }

  public static CreateOrderResponse of(List<OrderProduct> orderProducts) {
    List<OrderProductResponse> orderProductResponses = orderProducts.stream().map(
        orderProduct -> new OrderProductResponse(
            orderProduct.getProduct().getProductId(),
            orderProduct.getProduct().getProductName(),
            orderProduct.getOrderPrice(),
            orderProduct.getOrderQuantity()
        )).toList();

    Order order = orderProducts.get(0).getOrder();

    return new CreateOrderResponse(
        UUIDUtil.encodeToShort(order.getOrderNum()),
        order.getTotalPrice(),
        order.getReceiverName(),
        order.getReceiverPhoneNum(),
        order.getReceiverZipcode(),
        order.getReceiverAddressMain(),
        order.getReceiverAddressSub(),
        orderProductResponses
    );
  }

}
