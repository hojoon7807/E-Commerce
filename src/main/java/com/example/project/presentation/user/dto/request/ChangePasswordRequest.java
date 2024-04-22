package com.example.project.presentation.user.dto.request;

import com.example.project.application.user.usecase.ChangePasswordCommand;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record ChangePasswordRequest(
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W)(?=\\S+$).{8,15}$", message = "올바른 비밀번호 형식이 아닙니다.")
    String originalPassword,

    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W)(?=\\S+$).{8,15}$", message = "올바른 비밀번호 형식이 아닙니다.")
    String newPassword
) {

  public ChangePasswordCommand toCommand(Long userId) {
    return new ChangePasswordCommand(userId, originalPassword, newPassword);
  }
}
