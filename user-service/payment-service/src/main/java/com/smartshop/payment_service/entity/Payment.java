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
import lombok.Data;

@Data
@Entity
@Table(name="payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long paymentId;
	private long orderId;
	private BigDecimal paymentAmount;
	private String paymentStatus;
	private String paymentMethod;
	private String gateway;
	private String gatewayId;
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
