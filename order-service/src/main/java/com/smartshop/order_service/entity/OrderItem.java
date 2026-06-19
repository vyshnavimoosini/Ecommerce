package com.smartshop.order_service.entity;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name="orderitem")
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private long orderItemId;
	private long productId;
	@NotNull(message="OrderItem quantity is required")
	private Integer quantity;
	@NotNull(message="Order Price is required")
	private BigDecimal price;

}
