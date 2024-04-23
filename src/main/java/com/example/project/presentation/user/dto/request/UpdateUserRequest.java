package com.example.project.presentation.user.dto.request;

import com.example.project.application.user.usecase.UpdateUserCommand;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest(
    @NotBlank
    String phoneNum
) {

  public UpdateUserCommand toCommand(Long userId) {
    return new UpdateUserCommand(userId, phoneNum);
  }
}
