package com.example.project.presentation.user.dto.request;

import com.example.project.application.user.usecase.AddAddressCommand;
import jakarta.validation.constraints.NotEmpty;

public record AddAddressRequest(
    @NotEmpty
    String zipcode,
    @NotEmpty
    String addressMain,
    @NotEmpty
    String addressSub
) {

  public AddAddressCommand toCommand(Long userId) {
    return new AddAddressCommand(userId, zipcode, addressMain, addressSub);
  }
}
