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
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductRequestDto productRequestDto){
        ProductEntity product =  productService.createProduct(productRequestDto);
        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/getProductById")
    public ResponseEntity<ProductEntity> getProductById(@RequestParam Long productId){
        try {
            ProductEntity product = productService.getProductById(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/updateProduct")
    public ResponseEntity<ProductEntity> updateProduct(@RequestParam Long productId, @RequestBody ProductRequestDto productRequestDto){

        try {
            ProductEntity product = productService.updateProduct(productId, productRequestDto);
            return new ResponseEntity<>(product, HttpStatus.OK);
        } catch (RuntimeException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
    }


}
