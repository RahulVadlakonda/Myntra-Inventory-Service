package com.myntra.api.inventory.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryRequest {

	@NotNull(message = "PROD0001")
	private String productId;

	@Min(value = 1, message = "INVN0001")
	private Long quantity;
	
	private UpdateOperation operation;
}
