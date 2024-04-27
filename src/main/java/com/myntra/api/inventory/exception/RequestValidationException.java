package com.myntra.api.inventory.exception;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RequestValidationException extends RuntimeException {

	private Set<String> errors;
	
	public RequestValidationException(Set<String> errors) {
		super();
		this.errors = errors;
	}
}
