package com.example.project.domain.order.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
  DELIVERING, DELIVERED, PAYMENT_COMPLETE, PAYMENT_WAIT, PAYMENT_PROGRESS, TEMPORARY_ORDER,
  RESERVATION_ORDER
}
