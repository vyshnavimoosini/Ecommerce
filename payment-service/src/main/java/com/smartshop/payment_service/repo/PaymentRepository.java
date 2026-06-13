package com.smartshop.payment_service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartshop.payment_service.entity.Payment;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	
	List<Payment> findByPaymentStatus(String paymentStatus);
	List<Payment> findByGateway(String gateway);
	Payment findByIdempotencyKey(String idempotencyKey);
	Payment findByOrderId(Long orderId);
	

}
