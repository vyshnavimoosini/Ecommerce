package com.smartshop.payment_service.entity;

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
@Table(name="payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@NotNull
	private long paymentId;
	private long orderId;
	@NotNull(message="PaymentAmount is required")
	private BigDecimal paymentAmount;
	@NotBlank(message="PaymentStatus is required")
	private String paymentStatus;
	@NotBlank(message="PaymentMethod is required")
	private String paymentMethod;
	@NotBlank(message="Payment Gateway is required")
	private String gateway;
	@NotBlank(message="gatewayId is required")
	private String gatewayId;
	@NotBlank(message="idempotencyKey is required")
	private String idempotencyKey;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	
	@PrePersist
	protected void onCreate(){
		
		createdAt = LocalDateTime.now();
		
	}
	
	@PreUpdate
	protected void onUpdate(){
		
		updatedAt = LocalDateTime.now();
		
	}

}
