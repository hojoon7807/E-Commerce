package com.example.project.application.order.usecase;

import java.util.UUID;

public record RefundRequestCommand(
    Long userId,
    UUID orderNum
) {

}
