package com.myntra.api.inventory.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.myntra.api.inventory.entity.mongo.Inventory;
import com.myntra.api.inventory.entity.mongo.ProductGeneral;
import com.myntra.api.inventory.exception.RequestValidationException;
import com.myntra.api.inventory.request.InventoryRequest;
import com.myntra.api.inventory.response.InventoryResponse;
import com.myntra.api.inventory.service.InventoryService;
import com.myntra.api.inventory.util.InventoryQueryUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryServiceImpl implements InventoryService {
	
	private InventoryQueryUtil inventoryQueryUtil;
	
	private InventoryUpdateService inventoryUpdateService;

	@Override
	public ResponseEntity<InventoryResponse> getInventoryQuantity(String productId) {
		InventoryResponse response = new InventoryResponse();
		Inventory inventory = inventoryQueryUtil.findInvByProductId(productId);
		response.setProductId(inventory.getProduct().getId());
		response.setQuantity(inventory.getQuantity());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InventoryResponse> addNewInventory(InventoryRequest request) {
		InventoryResponse response = new InventoryResponse();
		ProductGeneral productGeneral = inventoryQueryUtil.findProductById(request.getProductId());
		if (null != inventoryQueryUtil.findInvByProductIdWithoutException(request.getProductId())) {
			Set<String> errorCodes = new HashSet<>();
			errorCodes.add("INVN0003");
			throw new RequestValidationException(errorCodes);
		}
		Inventory inventory = new Inventory();
		inventory.setProduct(productGeneral);
		inventory.setQuantity(request.getQuantity());
		inventoryQueryUtil.insertInventory(inventory);
		response.setProductId(inventory.getProduct().getId());
		response.setQuantity(inventory.getQuantity());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	@Override
	public ResponseEntity<InventoryResponse> updateInventory(InventoryRequest request) {
		InventoryResponse response = new InventoryResponse();
		if (request.getOperation() == null) {
			Set<String> errorCodes = new HashSet<>();
			errorCodes.add("INVN0004");
			throw new RequestValidationException(errorCodes);
		}
		Inventory inventory = inventoryQueryUtil.findInvByProductId(request.getProductId());
		inventoryUpdateService.updateQuantityAndSave(inventory, request);
		response.setProductId(inventory.getProduct().getId());
		response.setQuantity(inventory.getQuantity());;
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

}
