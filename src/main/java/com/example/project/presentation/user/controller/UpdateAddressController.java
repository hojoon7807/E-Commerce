package com.example.project.presentation.user.controller;

import com.example.project.application.user.usecase.UpdateAddressUseCase;
import com.example.project.domain.user.model.Address;
import com.example.project.presentation.user.dto.request.UpdateAddressRequest;
import com.example.project.presentation.user.dto.response.UpdateAddressResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
public class UpdateAddressController {

  private final UpdateAddressUseCase updateAddressUseCase;

  @PatchMapping("/user/addresses/{addressId}")
  public ResponseEntity<UpdateAddressResponse> updateAddress(
      @PathVariable @Min(1) Long addressId,
      @Valid @RequestBody UpdateAddressRequest updateAddressRequest,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = Long.valueOf(userDetails.getUsername());
    Address address = updateAddressUseCase.apply(updateAddressRequest.toCommand(userId, addressId));
    return ResponseEntity.ok(UpdateAddressResponse.of(address));
  }
}
