package com.example.project.presentation.user.dto.request;

import com.example.project.application.user.usecase.UpdateAddressCommand;
import jakarta.validation.constraints.NotBlank;

public record UpdateAddressRequest(

    @NotBlank
    String zipcode,
    @NotBlank
    String addressMain,
    @NotBlank
    String addressSub,
    boolean isDefault
) {

  public UpdateAddressCommand toCommand(Long userId, Long addressId) {
    return new UpdateAddressCommand(userId, addressId, zipcode, addressMain, addressSub, isDefault);
  }
}
