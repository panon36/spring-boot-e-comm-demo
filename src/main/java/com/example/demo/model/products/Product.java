package com.example.demo.model.products;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Product {

    private Long id;
    private String name;
    private String description;
    private Long categoryId;
    private Long brandId;
    private Double price;
    private Double salePrice;
    private Integer stock;
    private String imageUrl;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String unit;

}
