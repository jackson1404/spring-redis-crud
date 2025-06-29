package com.jackson.redis_crud_sample.service.serviceImpl;

import com.jackson.redis_crud_sample.model.ProductEntity;
import com.jackson.redis_crud_sample.repository.ProductRepository;
import com.jackson.redis_crud_sample.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
}