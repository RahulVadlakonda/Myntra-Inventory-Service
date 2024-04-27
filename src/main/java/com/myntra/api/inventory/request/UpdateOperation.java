package com.myntra.api.inventory.request;

import lombok.Getter;

@Getter
public enum UpdateOperation {

	ADD("Add quantity"),
	SUBTRACT("Subtract quantity");
	
	private String operation;
	
	private UpdateOperation(String operation) {
		this.operation=operation;
	}
	
}
