package com.example.project.application.user.usecase;

public record AddAddressCommand(
    Long userId,
    String zipcode,
    String addressMain,
    String addressSub
) {

}
