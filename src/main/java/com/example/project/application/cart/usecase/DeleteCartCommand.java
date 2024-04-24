package com.example.project.application.cart.usecase;

public record DeleteCartCommand(
    Long userId,
    Long cartId
) {

}
