package com.myntra.api.inventory.service;

import org.springframework.http.ResponseEntity;

import com.myntra.api.inventory.request.InventoryRequest;
import com.myntra.api.inventory.response.InventoryResponse;

public interface InventoryService {

	ResponseEntity<InventoryResponse> getInventoryQuantity(String productId);
	
	ResponseEntity<InventoryResponse> addNewInventory(InventoryRequest request);
	
	ResponseEntity<InventoryResponse> updateInventory(InventoryRequest request);
	
}
