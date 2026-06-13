package com.smartshop.inventory_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartshop.inventory_service.entity.Inventory;
import com.smartshop.inventory_service.service.InventoryService;

@RestController
@RequestMapping("/api/v1/inventory")
public class InventoryController {
	
	@Autowired
	InventoryService inventoryService;
	
	
	@PostMapping
	public String createInventory(@RequestBody Inventory inventory) {


		return inventoryService.createInventory(inventory);
	}
    
	@GetMapping("/{inventoryId}")
	public Inventory findByInventoryId(@PathVariable Long inventoryId) {

		return inventoryService.findByInventoryId(inventoryId);

	}

	@GetMapping("/reserved-stock/{reservedStock}")
	public List<Inventory> findByReservedStock(@PathVariable Integer reservedStock) {

		return inventoryService.findByReservedStock(reservedStock);
	}

	@GetMapping("{productId}")
	public Inventory findByProductId(@PathVariable Long productId) {

		return inventoryService.findByProductId(productId);

	}
    
	@GetMapping("/stock-threshold/{threshold}")
	public List<Inventory> findByStockLessThan(@RequestParam Integer threshold) {

		return inventoryService.findByStockLessThan(threshold);

	}
    
	@GetMapping
	public List<Inventory> findAll() {
		return inventoryService.findAll();
	}
	
	
	
    @PutMapping("/{inventoryId}")
	public Inventory updateInventory(@PathVariable long inventoryId, @RequestBody Inventory inventoryDetails) {

		

		return inventoryService.updateInventory(inventoryId, inventoryDetails);

	}
   
    @PutMapping("/reserve-stock/{productId}/{quantity}")
	public Inventory reserveStock(@PathVariable Long productId, @PathVariable Integer quantity) {
		
		return inventoryService.reserveStock(productId, quantity);
	}
  
    @PutMapping("/release-stock/{productId}/{quantity}")
	public Inventory releaseStock(@PathVariable Long productId, @PathVariable Integer quantity) {
		
		return inventoryService.releaseStock(productId, quantity);
	}


}
