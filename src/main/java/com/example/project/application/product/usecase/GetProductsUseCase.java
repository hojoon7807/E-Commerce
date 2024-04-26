package com.example.project.application.product.usecase;

import com.example.project.domain.product.model.Product;
import java.util.function.Function;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetProductsUseCase extends Function<Pageable, Page<Product>> {

}
