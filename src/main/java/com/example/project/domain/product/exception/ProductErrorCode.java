package com.example.project.domain.product.exception;

import com.example.project.presentation.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum ProductErrorCode implements ErrorCode {

  PRODUCT_NOT_FOUND("P001", "존재하지 않는 제품입니다.", 404);

  private final String code;
  private final String message;
  private final int status;

  @Override
  public String getCode() {
    return this.code;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  @Override
  public int getStatus() {
    return this.status;
  }

}
