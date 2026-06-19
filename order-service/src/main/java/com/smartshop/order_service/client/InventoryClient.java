package com.smartshop.order_service.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(name = "inventory-service")
public interface InventoryClient {
	
	

		
		@PutMapping("/api/v1/inventory/reserve-stock/{productId}/{quantity}")
		Object reserveStock(@PathVariable("productId") Long productId, @PathVariable("quantity") Integer quantity);

		@PutMapping("/api/v1/inventory/release-stock/{productId}/{quantity}")
		Object releaseStock(@PathVariable("productId") Long productId, @PathVariable("quantity") Integer quantity);
	


}
