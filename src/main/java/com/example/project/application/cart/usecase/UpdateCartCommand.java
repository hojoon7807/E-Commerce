package com.example.project.application.cart.usecase;

public record UpdateCartCommand(
    Long userId,
    Long cartId,
    int productQuantity
) {

}
