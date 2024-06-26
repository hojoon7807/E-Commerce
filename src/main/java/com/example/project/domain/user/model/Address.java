package com.example.project.domain.user.model;

import com.example.project.domain.common.EncryptConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Address {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long addressId;

  @ManyToOne(optional = false, fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

  @Column(nullable = false, columnDefinition = "char(52)")
  @Convert(converter = EncryptConverter.class)
  private String zipcode;

  @Column(nullable = false)
  @Convert(converter = EncryptConverter.class)
  private String addressMain;

  @Column(nullable = false)
  @Convert(converter = EncryptConverter.class)
  private String addressSub;

  @Column(nullable = false, columnDefinition = "tinyint(1)")
  private boolean isDefault;

  public boolean isSameUser(Long userId) {
    return user.getUserId().equals(userId);
  }

  public void updateAddress(String zipcode, String addressMain, String addressSub,
      boolean isDefault) {
    this.zipcode = zipcode;
    this.addressMain = addressMain;
    this.addressSub = addressSub;
    this.isDefault = isDefault;
  }

  @Builder
  public Address(Long addressId, User user, String zipcode, String addressMain, String addressSub,
      boolean isDefault) {
    this.addressId = addressId;
    this.user = user;
    this.zipcode = zipcode;
    this.addressMain = addressMain;
    this.addressSub = addressSub;
    this.isDefault = isDefault;
  }
}
