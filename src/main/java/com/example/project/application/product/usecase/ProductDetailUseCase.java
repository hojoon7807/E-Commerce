package com.example.project.application.product.usecase;

import com.example.project.domain.product.model.Product;
import java.util.function.Function;

public interface ProductDetailUseCase extends Function<Long, Product> {

}
