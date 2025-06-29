package com.jackson.redis_crud_sample.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/adminCache")
public class CacheController {

    @GetMapping("/clearProductCache")
    @CacheEvict(value = "products", allEntries = true)
    public ResponseEntity<?> clearProductCache(){

        return ResponseEntity.ok("Clean success");
    }

}
