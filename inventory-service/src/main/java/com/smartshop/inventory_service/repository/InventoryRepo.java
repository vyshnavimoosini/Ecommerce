package com.smartshop.inventory_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartshop.inventory_service.entity.Inventory;

@Repository
public interface InventoryRepo extends JpaRepository<Inventory, Long>{
	
	List<Inventory> findByReservedStock(Integer reservedStock);
	Inventory findByProductId(Long productId); 
	List<Inventory> findByStockLessThan(Integer threshold);
	

}
