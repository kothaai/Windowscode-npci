package com.npcisimulator.exception;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.npcisimulator.bean.ErrorResponse;

@RestControllerAdvice
public class FundTransferExceptionMapper {
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(value=FundTransferException.class)
	public ResponseEntity<ErrorResponse> handleCustomerException(FundTransferException fundTransferException)
	{
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setResponseCode(messageSource.getMessage("errorfund.response.code.500", null, null));
		errorResponse.setResponseMessage(messageSource.getMessage(errorResponse.getResponseCode(),null,null));
		
       ResponseEntity<ErrorResponse> responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			errorResponse.setResponseCode(fundTransferException.getResponseCode());
			errorResponse.setResponseMessage(fundTransferException.getResponseMessage());
			responseEntity = new ResponseEntity<ErrorResponse>(errorResponse,null,Integer.parseInt(fundTransferException.getHttpCode()));
			}
		catch(Exception e)	{
			e.printStackTrace();
		}
		return responseEntity;
}

}
