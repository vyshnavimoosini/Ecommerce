package com.smartshop.product_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="Products")
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private long productId;
	@NotBlank(message ="Product Name is required")
	private String productName;
	@NotNull(message="product price is required")
	private BigDecimal productPrice;
	@NotNull(message="product stock is required")
	private Integer productStock;
	@NotBlank(message="product description is required")
	private String productDescription;
	@NotBlank(message ="product imageurl is required")
	private String imageUrl;
	@NotBlank(message="product category is required")
	private String productCategory;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
	

}
