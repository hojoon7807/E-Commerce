package com.example.project.presentation.user.controller;

import com.example.project.application.user.usecase.AddAddressUseCase;
import com.example.project.presentation.user.dto.request.AddAddressRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddAddressController {

  private final AddAddressUseCase addAddressUseCase;

  @PostMapping("/user/addresses")
  public ResponseEntity<Object> addAddress(@RequestBody @Valid AddAddressRequest addAddressRequest,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    addAddressUseCase.accept(addAddressRequest.toCommand(userId));

    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
