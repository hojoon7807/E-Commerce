package com.example.project.presentation.user.controller;

import com.example.project.application.user.usecase.SignUpUseCase;
import com.example.project.presentation.user.dto.request.SignUpRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SignUpController {

  private final SignUpUseCase signUpUseCase;

  @PostMapping("/signup")
  public ResponseEntity<Object> signUp(@RequestBody @Valid SignUpRequest signUpRequest) {
    signUpUseCase.apply(signUpRequest.toCommand());

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

}
