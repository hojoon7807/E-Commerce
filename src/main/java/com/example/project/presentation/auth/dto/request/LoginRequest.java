package com.example.project.presentation.auth.dto.request;

import com.example.project.application.auth.usecase.LoginCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(
    @NotEmpty
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W)(?=\\S+$).{8,15}$", message = "올바른 비밀번호 형식이 아닙니다.")
    String password
) {

  public LoginCommand toCommand() {
    return new LoginCommand(email, password);
  }

}
