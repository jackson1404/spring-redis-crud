package com.jackson.redis_crud_sample.service;

import com.jackson.redis_crud_sample.dto.ProductRequestDto;
import com.jackson.redis_crud_sample.model.ProductEntity;

import java.util.List;

public interface ProductService {

    List<ProductEntity> getAllProduct();

    ProductEntity createProduct(ProductRequestDto productRequestDto);

    ProductEntity getProductById(Long productId);

    ProductEntity updateProduct(Long productId, ProductRequestDto productRequestDto);
}
