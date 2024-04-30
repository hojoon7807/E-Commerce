package com.example.project.application.order.usecase;

import java.util.UUID;

public record CancelOrderCommand(
    Long userId,
    UUID orderNum
) {

}
