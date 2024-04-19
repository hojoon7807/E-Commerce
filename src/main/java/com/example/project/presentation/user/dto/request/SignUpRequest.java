package com.example.project.presentation.user.dto.request;

import com.example.project.application.user.usecase.SignUpCommand;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public record SignUpRequest(
    @NotEmpty
    String username,
    @NotEmpty
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*\\W)(?=\\S+$).{8,15}$", message = "올바른 비밀번호 형식이 아닙니다.")
    String password,
    @NotEmpty
    @Email(message = "올바른 이메일 형식이 아닙니다.")
    String email,
    @NotEmpty
    @Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "올바른 전화번호 형식이 아닙니다.")
    String phoneNum,
    @NotEmpty
    String zipcode,
    @NotEmpty
    String addressMain,
    @NotEmpty
    String addressSub
) {

  public SignUpCommand toCommand() {
    return new SignUpCommand(username, password, email, phoneNum, zipcode, addressMain, addressSub);
  }
}
