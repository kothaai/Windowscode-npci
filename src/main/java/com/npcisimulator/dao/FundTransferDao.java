package com.npcisimulator.dao;

import com.npcisimulator.bean.FundTransferRequest;

public interface FundTransferDao {
	boolean accountValidation(String beneficiaryAccountNumber);
	boolean debitCheck(String amount, String beneficiaryAccountNumber);
	boolean creditFundTransfer(FundTransferRequest fundTransferRequest);
	boolean debitFundTransfer(FundTransferRequest fundTransferRequest);
	
	boolean inwardAccountValidation(String accountNumber);
	boolean inwardDebitCheck(String amount, String accountNumber);
	boolean inwardDebitFundTransfer(FundTransferRequest fundTransferRequest);
}
