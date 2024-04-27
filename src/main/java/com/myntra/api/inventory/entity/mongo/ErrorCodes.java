package com.myntra.api.inventory.entity.mongo;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "error_codes")
@Getter
@Setter
@NoArgsConstructor
public class ErrorCodes {

	@Id
	private long errorCodeSeqNum;
	
	private String code;
	
	private String description;
	
}
