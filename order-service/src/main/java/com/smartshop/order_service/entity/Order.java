package com.smartshop.order_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name= "orders")
public class Order {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private long orderId;
	private long userId;
	@NotNull(message="order totalAmount is required")
	private BigDecimal totalAmount;
	@NotBlank(message="order shippingAddress is required")
	private String shippingAddress;
	@NotBlank(message="order status is required")
	private String status;
	@NotBlank(message="order trackingId is required")
	private String trackingId;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "orderId")
	private List<OrderItem> orderItems;
	
	@PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

}
