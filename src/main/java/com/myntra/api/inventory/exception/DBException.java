package com.myntra.api.inventory.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DBException extends RuntimeException{

	private String errorCode;
	
	public DBException(String errorCode) {
		super(errorCode);
		this.errorCode = errorCode;
	}
}
