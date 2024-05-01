package com.example.project.domain.order.model;

import com.example.project.domain.common.EncryptConverter;
import com.example.project.domain.common.model.BaseTime;
import com.example.project.domain.order.exception.CanNotCancelOrderException;
import com.example.project.domain.order.exception.CanNotRefundOrderException;
import com.example.project.domain.order.exception.OrderUserMismatchException;
import com.example.project.domain.payment.Payment;
import com.example.project.domain.user.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@Entity
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order extends BaseTime {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long orderId;

  @Column(columnDefinition = "binary(16)")
  @Builder.Default
  private UUID orderNum = UUID.randomUUID();

  @Column(nullable = false, columnDefinition = "int unsigned")
  private int totalPrice;

  @Column(nullable = false, columnDefinition = "varchar(255)")
  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Column(nullable = false, columnDefinition = "char(130)")
  @Convert(converter = EncryptConverter.class)
  private String receiverName;

  @Column(nullable = false, columnDefinition = "char(60)")
  @Convert(converter = EncryptConverter.class)
  private String receiverPhoneNum;

  @Column(nullable = false, columnDefinition = "char(52)")
  @Convert(converter = EncryptConverter.class)
  private String receiverZipcode;

  @Column(nullable = false)
  @Convert(converter = EncryptConverter.class)
  private String receiverAddressMain;

  @Column(nullable = false)
  @Convert(converter = EncryptConverter.class)
  private String receiverAddressSub;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "payment_id")
  private Payment payment;

  public void updatePaymentComplete(Payment payment) {
    this.payment = payment;
    this.orderStatus = OrderStatus.PAYMENT_COMPLETE;
  }

  public void validateUser(Long userId) {
    if (!this.user.getUserId().equals(userId)) {
      throw new OrderUserMismatchException();
    }
  }

  public void cancelOrder() {
    if (!this.orderStatus.equals(OrderStatus.PAYMENT_COMPLETE)) {
      throw new CanNotCancelOrderException();
    }

    this.orderStatus = OrderStatus.CANCELED;
  }

  public void refundRequest() {
    LocalDate now = LocalDate.now();
    Period between = Period.between(this.getModifiedAt().toLocalDate(), now);

    if (!this.orderStatus.equals(OrderStatus.DELIVERED) || between.getDays() > 1) {
      throw new CanNotRefundOrderException();
    }

    this.orderStatus = OrderStatus.REFUND_REQUEST;
  }
}
