package com.npcisimulator.dao;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.npcisimulator.bean.FundTransferRequest;

@Repository("fundTransferDao")
public class FundTransferDaoImpl implements FundTransferDao {

	@Autowired
	private MessageSource messageSource;
	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	public static final Logger LOGGER = LoggerFactory.getLogger(FundTransferDaoImpl.class);


	@Override
	public boolean accountValidation(String beneficiaryAccountNumber) {
		String query = messageSource.getMessage("accountValidation.validate", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("beneficiaryAccountNumber",beneficiaryAccountNumber);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	

	@Override
	public boolean debitCheck(String amount,String beneficiaryAccountNumber) {
		String query = messageSource.getMessage("balance.check", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("amount",amount);
		namedParameters.put("beneficiaryAccountNumber",beneficiaryAccountNumber);
		LOGGER.info("SQL{} FilterParams{}",query, namedParameters);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	

	@Override
	public boolean creditFundTransfer(FundTransferRequest fundTransferRequest) {
		String query = messageSource.getMessage("creditamount.balance", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("accountNumber", fundTransferRequest.getAccountNumber());
		namedParameters.put("beneficiaryAccountNumber", fundTransferRequest.getBeneficiaryAccountNumber());
		namedParameters.put("amount", fundTransferRequest.getAmount());
		namedParameters.put("transferType", fundTransferRequest.getTransferType());

		LOGGER.info("SQL{}, FilterParams{}", query, namedParameters);
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if (count > 0) {
			result = true;

		}
		return result;
	}

	@Override
	public boolean debitFundTransfer(FundTransferRequest fundTransferRequest) {
		String query = messageSource.getMessage("debitamount.balance", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("accountNumber", fundTransferRequest.getAccountNumber());
		namedParameters.put("beneficiaryAccountNumber", fundTransferRequest.getBeneficiaryAccountNumber());
		namedParameters.put("amount", fundTransferRequest.getAmount());
		namedParameters.put("transferType", fundTransferRequest.getTransferType());

		LOGGER.info("SQL{}, FilterParams{}", query, namedParameters);
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if (count > 0) {
			result = true;

		}
		return result;
	}


	@Override
	public boolean inwardAccountValidation(String accountNumber) {
		String query = messageSource.getMessage("inwardaccountValidation.validate", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("accountNumber",accountNumber);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}


	@Override
	public boolean inwardDebitCheck(String amount, String accountNumber) {
		String query = messageSource.getMessage("inwardbalance.check", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("amount",amount);
		namedParameters.put("accountNumber",accountNumber);
		LOGGER.info("SQL{} FilterParams{}",query, namedParameters);
		
		int count = namedParameterJdbcTemplate.queryForObject(query, namedParameters, Integer.class);
		boolean result = false;
		if(count>0) {
			result = true;
			
		}
		return result;
	}
	


	@Override
	public boolean inwardDebitFundTransfer(FundTransferRequest fundTransferRequest) {
		String query = messageSource.getMessage("inwarddebitamount.balance", null, null);
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("accountNumber", fundTransferRequest.getAccountNumber());
		namedParameters.put("amount", fundTransferRequest.getAmount());
		namedParameters.put("transferType", fundTransferRequest.getTransferType());

		LOGGER.info("SQL{}, FilterParams{}", query, namedParameters);
		int count = namedParameterJdbcTemplate.update(query, namedParameters);
		boolean result = false;
		if (count > 0) {
			result = true;

		}
		return result;
	}

}
