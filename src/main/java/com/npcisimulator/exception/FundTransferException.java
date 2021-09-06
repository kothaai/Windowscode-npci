package com.npcisimulator.exception;

import com.npcisimulator.bean.FundTransferRequest;

public class FundTransferException extends RuntimeException{
	private String responseCode;
	private String responseMessage;
	private String transactionId;
	private String transactionDateTime;
	private Object[] object;
	private String httpCode;
	private FundTransferRequest fundTransferRequest;
	public FundTransferException(String responseCode, String responseMessage, String transactionId,
			String transactionDateTime, Object[] object, String httpCode, FundTransferRequest fundTransferRequest) {
		super();
		this.responseCode = responseCode;
		this.responseMessage = responseMessage;
		this.transactionId = transactionId;
		this.transactionDateTime = transactionDateTime;
		this.object = object;
		this.httpCode = httpCode;
		this.fundTransferRequest = fundTransferRequest;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public String getTransactionDateTime() {
		return transactionDateTime;
	}
	public Object[] getObject() {
		return object;
	}
	public String getHttpCode() {
		return httpCode;
	}
	public FundTransferRequest getFundTransferRequest() {
		return fundTransferRequest;
	}


}
