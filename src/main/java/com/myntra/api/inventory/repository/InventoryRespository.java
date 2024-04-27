package com.myntra.api.inventory.repository;

import org.springframework.data.repository.CrudRepository;

import com.myntra.api.inventory.entity.mongo.Inventory;

public interface InventoryRespository extends CrudRepository<Inventory, String> {
	
//	Inventory findTop1ByProductId(String productId);
	
	Inventory findTop1ByProduct(String productId);
	
}
