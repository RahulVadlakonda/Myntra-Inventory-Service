package com.myntra.api.inventory.service.impl;

import org.springframework.stereotype.Component;

import com.myntra.api.inventory.entity.mongo.Inventory;
import com.myntra.api.inventory.request.InventoryRequest;
import com.myntra.api.inventory.request.UpdateOperation;
import com.myntra.api.inventory.util.InventoryQueryUtil;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class InventoryUpdateService {
	
	private InventoryQueryUtil inventoryQueryUtil;

	public void updateQuantityAndSave(Inventory inventory, InventoryRequest request) {
		Long quantity = inventory.getQuantity();
		if (UpdateOperation.ADD.equals(request.getOperation())) {
			quantity += inventory.getQuantity();
		}
		else {
			quantity -= inventory.getQuantity();
		}
		inventory.setQuantity(quantity);
		inventoryQueryUtil.insertInventory(inventory);
	}

}
