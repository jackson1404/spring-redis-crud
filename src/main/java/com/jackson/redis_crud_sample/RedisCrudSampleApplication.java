package com.jackson.redis_crud_sample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisCrudSampleApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisCrudSampleApplication.class, args);
	}

}
