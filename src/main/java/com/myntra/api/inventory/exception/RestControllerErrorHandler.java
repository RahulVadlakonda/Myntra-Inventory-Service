package com.myntra.api.inventory.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.myntra.api.inventory.controller.InventoryController;
import com.myntra.api.inventory.response.BaseResponse;
import com.myntra.api.inventory.util.ErrorConstants;
import com.myntra.api.inventory.util.ErrorUtil;

import lombok.RequiredArgsConstructor;

@ControllerAdvice(basePackageClasses = InventoryController.class)
@RequiredArgsConstructor
public class RestControllerErrorHandler {
	
	private final ErrorUtil errorUtil;

	@ExceptionHandler(RequestValidationException.class)
	public ResponseEntity<BaseResponse> handleRequestDataValidationException(RequestValidationException ex) throws Exception {
		BaseResponse response = new BaseResponse();
		response.setErrors(errorUtil.getErrors(ex.getErrors()));
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<BaseResponse> handleEntityNotFoundException(EntityNotFoundException ex) throws Exception {
		BaseResponse response = new BaseResponse();
		response.setErrors(errorUtil.getErrors(ex.getErrorCode()));
		return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<BaseResponse> handleGeneralException(Exception ex) throws Exception {
		BaseResponse response = new BaseResponse();
		response.setErrors(errorUtil.getErrors(ErrorConstants.INTERNAL_SERVER_ERROR));
		return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler({JsonMappingException.class, JsonProcessingException.class})
	public ResponseEntity<BaseResponse> handleJsonMappingException(Exception e) throws Exception {
		BaseResponse response = new BaseResponse();
		response.setErrors(errorUtil.getErrors(ErrorConstants.INVALID_JSON));
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({HttpMessageNotReadableException.class, ServletRequestBindingException.class})
	public ResponseEntity<BaseResponse> handleRequestFieldsException(Exception ex) throws Exception {
		BaseResponse response = new BaseResponse();
		response.setErrors(errorUtil.getErrors(ErrorConstants.INVALID_JSON));
		return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
	}
}
