package com.myntra.api.inventory.util;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.myntra.api.inventory.response.Error;

@Component
public class ErrorUtil {
	
	public Set<Error> getErrors(Set<String> errorCodes) {
		Set<Error> errors = new HashSet<>();
		if (!CollectionUtils.isEmpty(errorCodes)) {
			errorCodes.parallelStream().forEach(errorCode -> {
				Error error = new Error();
				error.setCode(errorCode);
				error.setDescription(SystemStartup.errorCodesMap.get(errorCode));
				errors.add(error);
			});
		}
		return errors;
	}

	public Set<Error> getErrors(String errorCode) {
		Set<Error> errors = new HashSet<>();
		Error error = new Error();
		error.setCode(errorCode);
		error.setDescription(SystemStartup.errorCodesMap.get(errorCode));
		errors.add(error);
		return errors;
	}

}
