package com.smartshop.payment_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smartshop.payment_service.entity.Payment;
import com.smartshop.payment_service.service.PaymentService;

@RestController
@RequestMapping("/api/v1/payments")
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping
	public String savePayment(@RequestBody Payment payment) {


		return paymentService.savePayment(payment);
	}
	
	@GetMapping("/paymentId/{paymentId}")
	public Payment findByPaymentId(@PathVariable Long paymentId) {

		return paymentService.findByPaymentId(paymentId);
	}
   
	@GetMapping("/status/{paymentStatus}")
	public List<Payment> findByPaymentStatus(@PathVariable  String paymentStatus) {

		return paymentService.findByPaymentStatus(paymentStatus);
	}
	@GetMapping("/gateway/{gateway}")
	public List<Payment> findByGateway(@PathVariable String gateway) {
		return paymentService.findByGateway(gateway);
	}
    
	@GetMapping("/idempotencyKey/{idempotencyKey}")
	public Payment findByIdempotencyKey(@PathVariable  String idempotencyKey) {
		return paymentService.findByIdempotencyKey(idempotencyKey);
	}
    
	@GetMapping("/orderId/{orderId}")
	public Payment findByOrderId(@PathVariable Long orderId) {
		return paymentService.findByOrderId(orderId);
	}
	
	@PutMapping("/{paymentId}")
	public Payment updatePaymentStatus(@PathVariable Long paymentId, @RequestBody Payment paymentDetails) {
	
		
		
		return paymentService.updatePaymentStatus(paymentId, paymentDetails);
	}
 
	@PutMapping("/refund/{paymentId}")
	public Payment processRefund(@PathVariable Long paymentId) {
		
		return paymentService.processRefund(paymentId);
	}

}
