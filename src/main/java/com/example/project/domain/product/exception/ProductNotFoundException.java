package com.example.project.domain.product.exception;

import com.example.project.presentation.common.error.BusinessException;

public class ProductNotFoundException extends BusinessException {

  public ProductNotFoundException() {
    super(ProductErrorCode.PRODUCT_NOT_FOUND);
  }
}
