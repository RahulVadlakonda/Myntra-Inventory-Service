package com.myntra.api.inventory.aspect;

import java.time.LocalDateTime;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import lombok.Data;

@Component
@Data
@RequestScope
public class RequestContext {
	
	private String methodName;
	
	private Object[] args;

	private LocalDateTime transactionStartTime;
}
