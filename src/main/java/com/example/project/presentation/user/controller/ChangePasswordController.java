package com.example.project.presentation.user.controller;

import com.example.project.application.user.usecase.ChangePasswordUseCase;
import com.example.project.presentation.user.dto.request.ChangePasswordRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ChangePasswordController {

  private final ChangePasswordUseCase changePasswordUseCase;

  @PatchMapping("/user/password")
  public void changePassword(@RequestBody @Valid ChangePasswordRequest changePasswordRequest,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    changePasswordUseCase.accept(changePasswordRequest.toCommand(userId));
  }
}
