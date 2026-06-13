package com.smartshop.chatbot.dto;

import lombok.Data;

@Data
public class ProductDTO {
    private Long productId;
    private String productName;
    private Double productPrice;
    private Integer productStock;
    private String productDescription;
    private String imageUrl;
    private String productCategory;
}
