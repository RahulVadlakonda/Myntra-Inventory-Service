package com.myntra.api.inventory.response;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class BaseResponse {
	
	private Transaction transaction;
	
	private Set<Error> errors;
}
