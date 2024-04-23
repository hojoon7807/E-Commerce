package com.example.project.application.cart.usecase;

public record AddCartCommand(
    Long userId,
    Long productId,
    int productQuantity
) {

}
