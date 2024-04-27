package com.myntra.api.inventory.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myntra.api.inventory.response.InventoryResponse;
import com.myntra.api.inventory.service.InventoryService;
import com.myntra.api.inventory.validator.RequestDataValidator;
import com.myntra.api.inventory.exception.RequestValidationException;
import com.myntra.api.inventory.request.InventoryRequest;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/api/v1/inventory")
@AllArgsConstructor
public class InventoryController {
	
	private InventoryService inventoryService;
	
	private RequestDataValidator validator;

	@GetMapping()
	public ResponseEntity<InventoryResponse> getInventoryQuantity(
			@RequestParam(value = "productId", required = false) String productId) {
		if (null == productId) {
			Set<String> errorCodes = new HashSet<>();
			errorCodes.add("PROD0001");
			throw new RequestValidationException(errorCodes);
		}
		return inventoryService.getInventoryQuantity(productId);
	}
	
	@PostMapping
	public ResponseEntity<InventoryResponse> addNewInventory(@RequestBody InventoryRequest request) {
		validator.validate(request);
		return inventoryService.addNewInventory(request);
	}
	
	@PutMapping
	public ResponseEntity<InventoryResponse> updateInventory(@RequestBody InventoryRequest request) {
		validator.validate(request);
		return inventoryService.updateInventory(request);
	}
	
}
