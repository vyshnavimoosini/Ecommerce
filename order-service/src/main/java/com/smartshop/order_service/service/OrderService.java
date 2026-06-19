package com.smartshop.order_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartshop.order_service.client.InventoryClient;
import com.smartshop.order_service.client.PaymentClient;
import com.smartshop.order_service.entity.Order;
import com.smartshop.order_service.entity.OrderItem;
import com.smartshop.order_service.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private InventoryClient inventoryClient;
	
	@Autowired
	private PaymentClient paymentClient;

	@Autowired
	OrderRepository orderRepository;

	public List<Order> getByUserId(long userId) {

		return orderRepository.findByUserId(userId);

	}

	public String createOrder(Order order) {
	    // Step 1: Reserve stock for each item
	    for (OrderItem item : order.getOrderItems()) {
	        inventoryClient.reserveStock(item.getProductId(), item.getQuantity());
	    }

	    // Step 2: Save order with status PENDING
	    order.setStatus("PENDING");
	    orderRepository.save(order);

	    // Step 3: Process payment
	    try {
	        Map<String, Object> payment = new HashMap<>();
	        payment.put("orderId", order.getOrderId());
	        payment.put("paymentAmount", order.getTotalAmount());
	        payment.put("paymentStatus", "COMPLETED");
	        payment.put("paymentMethod", "CARD");
	        payment.put("gateway", "STRIPE");
	        payment.put("gatewayId", "GW-" + order.getOrderId());
	        payment.put("idempotencyKey", "IK-" + order.getOrderId());
	        paymentClient.savePayment(payment);

	        order.setStatus("CONFIRMED");
	        orderRepository.save(order);
	    } catch (Exception e) {
	        // Step 4: If payment fails, release stock
	        for (OrderItem item : order.getOrderItems()) {
	            inventoryClient.releaseStock(item.getProductId(), item.getQuantity());
	        }
	        order.setStatus("FAILED");
	        orderRepository.save(order);
	        throw new RuntimeException("Payment failed: " + e.getMessage());
	    }

	    return "Order created successfully";
	}

	public List<Order> findByTrackingId(String trackingId) {

		return orderRepository.findByTrackingId(trackingId);
	}

	public Order getByOrderId(long orderId) {

		return orderRepository.findById(orderId).orElse(null);

	}

	public List<Order> getAll() {

		return orderRepository.findAll();

	}

	public List<Order> findByStatus(String status) {

		return orderRepository.findByStatus(status);
	}

	public Order updateOrder(long orderId, Order orderDetails) {

		Order order = orderRepository.findById(orderId).orElse(null);

		order.setStatus(orderDetails.getStatus());
		order.setTrackingId(orderDetails.getTrackingId());
		order.setShippingAddress(orderDetails.getShippingAddress());

		return orderRepository.save(order);

	}
    
	public Order cancelOrder(long orderId) {

		Order order = orderRepository.findById(orderId).orElse(null);

		order.setStatus("CANCELLED");

		return orderRepository.save(order);

	}
	
	public String deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
		
		return "Order Deleted";
	}
}
