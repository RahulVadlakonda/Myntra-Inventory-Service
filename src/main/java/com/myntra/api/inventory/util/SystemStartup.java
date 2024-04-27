package com.myntra.api.inventory.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myntra.api.inventory.entity.mongo.ErrorCodes;
import com.myntra.api.inventory.repository.ErrorCodesRepository;

import jakarta.annotation.PostConstruct;

@Component
public class SystemStartup {
	
	public static Map<String, String> errorCodesMap;
	
	@Autowired
	private ErrorCodesRepository errorCodesRepository;
	
	@PostConstruct
	private void loadValues() {
		loadErrorCodes();
	}

	private void loadErrorCodes() {
		errorCodesMap = new HashMap<>();
		List<ErrorCodes> errorCodes = errorCodesRepository.findAll();
		for (ErrorCodes errorCode : errorCodes) {
			errorCodesMap.put(errorCode.getCode(), errorCode.getDescription());
		}
	}

}