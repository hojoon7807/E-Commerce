package com.example.project.domain.user.model;

import jakarta.persistence.Column;
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

  @Column(nullable = false, columnDefinition = "char(10)")
  private String zipcode;

  @Column(nullable = false)
  private String addressMain;

  @Column(nullable = false)
  private String addressSub;

  @Column(nullable = false, columnDefinition = "tinyint(1)")
  private boolean isDefault;

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
