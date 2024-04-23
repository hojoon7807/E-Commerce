package com.example.project.application.user.usecase;

public record UpdateAddressCommand(
    Long userId,
    Long addressId,
    String zipcode,
    String addressMain,
    String addressSub,
    boolean isDefault
) {

}
