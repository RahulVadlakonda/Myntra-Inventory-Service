package com.myntra.api.inventory.aspect;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.myntra.api.inventory.response.BaseResponse;
import com.myntra.api.inventory.response.Transaction;

@Aspect
@Component
public class TransactionAspect {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionAspect.class);

	@Autowired
	private RequestContext requestContext;

	@Around(value = """
			execution(* com.myntra.api.inventory.controller.InventoryController.*(..))
			""")
	public Object aroundAspect(ProceedingJoinPoint joinPoint) throws Throwable {
		LocalDateTime transactionStartTime = LocalDateTime.now();
		requestContext.setArgs(joinPoint.getArgs());
		requestContext.setMethodName(joinPoint.getSignature().getName());
		requestContext.setTransactionStartTime(LocalDateTime.now());
		Object proceed = joinPoint.proceed();
		ResponseEntity<BaseResponse> baseResponse = (ResponseEntity<BaseResponse>) proceed;
		setTransaction(baseResponse.getBody(), requestContext);
		return baseResponse;
	}

	@AfterReturning(value = """
			execution(* com.myntra.api.inventory.exception.RestControllerErrorHandler.*(..))
			""", returning = "object")
	public Object aroundException(JoinPoint joinPoint, Object object) {
		ResponseEntity<BaseResponse> baseResponse = (ResponseEntity<BaseResponse>) object;
		requestContext.setTransactionStartTime(LocalDateTime.now());
		setTransaction(baseResponse.getBody(), requestContext);
		return baseResponse;
	}

	private void setTransaction(BaseResponse baseResponse, RequestContext requestContext) {
		Transaction transaction = new Transaction();
		transaction.setRequestTimeStamp(requestContext.getTransactionStartTime().toString());
		LocalDateTime transactionEndTime = LocalDateTime.now();
		transaction.setResponseTimeStamp(transactionEndTime.toString());
		transaction
				.setDuration(ChronoUnit.MILLIS.between(requestContext.getTransactionStartTime(), transactionEndTime));
		baseResponse.setTransaction(transaction);
	}
}
