package com.smartshop.payment_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartshop.payment_service.entity.Payment;
import com.smartshop.payment_service.repo.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	PaymentRepository paymentRepository;

	public String savePayment(Payment payment) {

		paymentRepository.save(payment);

		return "Payment Saved";
	}

	public Payment findByPaymentId(Long paymentId) {

		return paymentRepository.findById(paymentId).orElse(null);
	}

	public List<Payment> findByPaymentStatus(String paymentStatus) {

		return paymentRepository.findByPaymentStatus(paymentStatus);
	}

	public List<Payment> findByGateway(String gateway) {
		return paymentRepository.findByGateway(gateway);
	}

	public Payment findByIdempotencyKey(String idempotencyKey) {
		return paymentRepository.findByIdempotencyKey(idempotencyKey);
	}

	public Payment findByOrderId(Long orderId) {
		return paymentRepository.findByOrderId(orderId);
	}
	public Payment updatePaymentStatus(Long paymentId, Payment paymentDetails) {
		Payment payment=paymentRepository.findById(paymentId).orElse(null);
		payment.setPaymentStatus(paymentDetails.getPaymentStatus());
		
		return paymentRepository.save(payment);
	}
 
	public Payment processRefund(Long paymentId) {
		Payment payment=paymentRepository.findById(paymentId).orElse(null);
		payment.setPaymentStatus("REFUNDED");
		return paymentRepository.save(payment);
	}

	public List<Payment> findAll() {
		// TODO Auto-generated method stub
		return paymentRepository.findAll();
	}
	
	public String deleteByPaymentId(Long paymentId) {
		paymentRepository.deleteById(paymentId);
		return "Payment deleted";
		
	}
}
