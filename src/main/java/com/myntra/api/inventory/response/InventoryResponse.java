package com.myntra.api.inventory.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryResponse extends BaseResponse{

	private String productId;
	
	private Long quantity;
}
