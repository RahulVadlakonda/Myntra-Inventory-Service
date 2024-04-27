package com.myntra.api.inventory.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EntityNotFoundException extends RuntimeException{

	private String errorCode;
	
	public EntityNotFoundException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}
}
