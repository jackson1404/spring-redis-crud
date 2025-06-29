package com.jackson.redis_crud_sample.dto;

import lombok.Data;

@Data
public class ProductRequestDto {

    private String productName;

    private Double productPrice;

    private String productDescription;

    private Integer productQuantity;
}
