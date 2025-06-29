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

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @Autowired
    private ProductRepository productRepository;

    @Override
    @Cacheable(value = "products")
    public List<ProductEntity> getAllProduct() {
        logger.info("Hit the db");
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

        logger.info("call save");
        return productRepository.save(productEntity);
    }
}