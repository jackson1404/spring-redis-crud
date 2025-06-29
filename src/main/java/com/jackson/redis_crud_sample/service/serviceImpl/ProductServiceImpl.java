package com.jackson.redis_crud_sample.service.serviceImpl;

import com.jackson.redis_crud_sample.dto.ProductRequestDto;
import com.jackson.redis_crud_sample.model.ProductEntity;
import com.jackson.redis_crud_sample.repository.ProductRepository;
import com.jackson.redis_crud_sample.service.ProductService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Cacheable(value = "products")
    public List<ProductEntity> getAllProduct() {
        logger.info("Hit the db server for get all products");
        return productRepository.findAll();
    }

    @Override
    @Transactional
    @Caching(
            put = @CachePut(value = "product", key = "#result.productId"),
            evict = @CacheEvict(value = "products", allEntries = true)
    )
    public ProductEntity createProduct(ProductRequestDto productRequestDto) {

        ProductEntity productEntity = new ProductEntity();
        productEntity.setProductName(productRequestDto.getProductName());
        productEntity.setProductDescription(productRequestDto.getProductDescription());
        productEntity.setProductPrice(productRequestDto.getProductPrice());
        productEntity.setProductQuantity(productRequestDto.getProductQuantity());

        logger.info("hit the db service for create");
        return productRepository.save(productEntity);
    }

    @Override
    @Cacheable(value = "product", key = "#productId")
    public ProductEntity getProductById(Long productId) {

        System.out.println("hit the db service for get product by id " + productId);
        return productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

    }

    @Override
    @Caching(
            put = @CachePut(value = "product", key = "#productId"),
            evict = @CacheEvict(value = "products", allEntries = true)
    )
    public ProductEntity updateProduct(Long productId, ProductRequestDto productRequestDto) {

        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        productEntity.setProductName(productRequestDto.getProductName());
        productEntity.setProductPrice(productRequestDto.getProductPrice());
        productEntity.setProductDescription(productRequestDto.getProductDescription());
        productEntity.setProductQuantity(productRequestDto.getProductQuantity());
        logger.info("hit the db from service for update product");
        productRepository.save(productEntity);
        return productEntity;
    }
}