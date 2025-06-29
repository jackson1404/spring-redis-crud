package com.jackson.redis_crud_sample.controller;

import com.jackson.redis_crud_sample.dto.ProductRequestDto;
import com.jackson.redis_crud_sample.model.ProductEntity;
import com.jackson.redis_crud_sample.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @GetMapping("/getAllProducts")
    public ResponseEntity<List<ProductEntity>> getAllProducts() {
        List<ProductEntity> products = productService.getAllProduct();
        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(30, TimeUnit.MINUTES))
                .body(products);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductEntity product =  productService.createProduct(productRequestDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }


}
