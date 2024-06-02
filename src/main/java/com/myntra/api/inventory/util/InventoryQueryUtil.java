package com.myntra.api.inventory.util;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.myntra.api.inventory.entity.mongo.Inventory;
import com.myntra.api.inventory.exception.EntityNotFoundException;
import com.myntra.api.inventory.repository.InventoryRespository;
import com.myntra.api.inventory.repository.ProductGeneralRepository;

import lombok.AllArgsConstructor;

import com.myntra.api.inventory.entity.mongo.ProductGeneral;
import com.myntra.api.inventory.exception.DBException;

@Component
@AllArgsConstructor
public class InventoryQueryUtil {

	private InventoryRespository inventoryRespository;

	private ProductGeneralRepository productGeneralRepository;

	public Inventory findInvByProductId(String productId) {
		
		Inventory inventory = inventoryRespository.findTop1ByProduct(productId);
		if (null == inventory) {
			throw new EntityNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND_IN_INVENTORY);
		}
		return inventory;
	}
	
	public Inventory findInvByProductIdWithoutException(String productId) {
		return inventoryRespository.findTop1ByProduct(productId);
	}

	public ProductGeneral findProductById(String productId) {
		Optional<ProductGeneral> productOptional = null;
		try {
			productOptional = productGeneralRepository.findById(productId);
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}
		if (productOptional.isEmpty()) {
			throw new EntityNotFoundException(ErrorConstants.PRODUCT_NOT_FOUND);
		}
		return productOptional.get();
	}

	public void insertInventory(Inventory inventory) {
		try {
			inventoryRespository.save(inventory);
		} catch (Exception e) {
			throw new DBException(e.getMessage());
		}

	}
}
