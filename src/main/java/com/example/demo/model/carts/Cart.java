package com.example.demo.model.carts;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Cart {

    private Long id;
    private Long userId;
    private Long productId;
    private Integer quantity;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
