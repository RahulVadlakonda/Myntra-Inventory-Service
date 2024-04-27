package com.myntra.api.inventory.validator;

import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.myntra.api.inventory.exception.RequestValidationException;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RequestDataValidator {

	private final Validator validator;
	
	public void validate(Object objToValidate) {
		Set<ConstraintViolation<Object>> constraintValidations = validator.validate(objToValidate);
		
		if (null != constraintValidations && constraintValidations.size() > 0) {
			Set<String> errorCodes = new HashSet<>();
			constraintValidations.forEach(
					constraintValidation -> errorCodes.add(constraintValidation.getMessage())
					);
			throw new RequestValidationException(errorCodes);
		}
	}
}
