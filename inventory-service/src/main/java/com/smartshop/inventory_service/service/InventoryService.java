package com.smartshop.inventory_service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smartshop.inventory_service.entity.Inventory;
import com.smartshop.inventory_service.repository.InventoryRepo;

@Service
public class InventoryService {

	@Autowired
	InventoryRepo inventoryRepo;

	public String createInventory(Inventory inventory) {

		inventoryRepo.save(inventory);

		return "Inventory Saved";
	}

	public Inventory findByInventoryId(Long inventoryId) {

		return inventoryRepo.findById(inventoryId).orElse(null);

	}

	public List<Inventory> findByReservedStock(Integer reservedStock) {

		return inventoryRepo.findByReservedStock(reservedStock);
	}

	public Inventory findByProductId(Long productId) {

		return inventoryRepo.findByProductId(productId);

	}

	public List<Inventory> findByStockLessThan(Integer threshold) {

		return inventoryRepo.findByStockLessThan(threshold);

	}

	public List<Inventory> findAll() {
		return inventoryRepo.findAll();
	}

	public Inventory updateInventory(long inventoryId, Inventory inventoryDetails) {

		Inventory inventory = inventoryRepo.findById(inventoryId).orElse(null);

		inventory.setReservedStock(inventoryDetails.getReservedStock());

		inventory.setStock(inventoryDetails.getStock());

		return inventoryRepo.save(inventory);

	}

	public Inventory reserveStock(Long productId, Integer quantity) {
		Inventory inventory = inventoryRepo.findByProductId(productId);

		if (inventory.getStock() < quantity) {
			throw new RuntimeException("Not enough stock available");
		}

		inventory.setStock(inventory.getStock() - quantity);
		inventory.setReservedStock(inventory.getReservedStock() + quantity);

		return inventoryRepo.save(inventory);
	}

	public Inventory releaseStock(Long productId, Integer quantity) {
		Inventory inventory = inventoryRepo.findByProductId(productId);

		inventory.setStock(inventory.getStock() + quantity);
		inventory.setReservedStock(inventory.getReservedStock() - quantity);

		return inventoryRepo.save(inventory);
	}

}
