package com.myntra.api.inventory.request;

import com.myntra.api.inventory.util.ErrorConstants;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class InventoryRequest {

	@NotNull(message = ErrorConstants.INVALID_PRODUCT_ID)
	private String productId;

	@Min(value = 1, message = ErrorConstants.INVALID_QUANTITY)
	private Long quantity;
	
	@NotNull(message = ErrorConstants.INVALID_UPDATE_OPERATION)
	private UpdateOperation operation;
}
