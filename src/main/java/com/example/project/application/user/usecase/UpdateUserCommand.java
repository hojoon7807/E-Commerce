package com.example.project.application.user.usecase;

public record UpdateUserCommand(
    Long userId,
    String phoneNum
) {

}
