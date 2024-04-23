package com.example.project.presentation.user.controller;

import com.example.project.application.user.usecase.UpdateUserUseCase;
import com.example.project.presentation.user.dto.request.UpdateUserRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UpdateUserController {

  private final UpdateUserUseCase updateUserUseCase;

  @PatchMapping("/user")
  public ResponseEntity<Object> updateUser(
      @RequestBody @Valid UpdateUserRequest updateUserRequest,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    updateUserUseCase.accept(updateUserRequest.toCommand(userId));
    return ResponseEntity.ok().build();
  }
}
