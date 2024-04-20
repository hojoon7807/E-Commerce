package com.example.project.presentation.auth.controller;

import com.example.project.application.auth.usecase.LoginUseCase;
import com.example.project.domain.auth.model.JwtToken;
import com.example.project.domain.user.model.User;
import com.example.project.infrastructure.JwtProvider;
import com.example.project.presentation.auth.dto.request.LoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

  private final LoginUseCase loginUseCase;
  private final JwtProvider jwtProvider;

  @PostMapping("/auth/login")
  public ResponseEntity<JwtToken> login(@RequestBody @Valid LoginRequest loginRequest) {
    User user = loginUseCase.apply(loginRequest.toCommand());

    String accessToken = jwtProvider.createAccessToken(user);
    String refreshToken = jwtProvider.createRefreshToken(user);

    JwtToken jwtToken = new JwtToken(accessToken, refreshToken);

    return ResponseEntity.ok(jwtToken);
  }
}
