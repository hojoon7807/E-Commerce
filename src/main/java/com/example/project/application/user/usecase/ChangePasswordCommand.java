package com.example.project.application.user.usecase;

public record ChangePasswordCommand(
    Long userId,
    String originalPassword,
    String newPassword
) {

}
