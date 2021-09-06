package com.npcisimulator.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.npcisimulator.bean.FundTransferRequest;
import com.npcisimulator.bean.FundTransferResponse;
import com.npcisimulator.dao.FundTransferDao;
import com.npcisimulator.exception.FundTransferException;
import com.npcisimulator.util.NPCIConstants;

@Service("FundTransferService")
public class FundTransferServiceImpl implements FundTransferService {

	public static final Logger LOGGER = LoggerFactory.getLogger(FundTransferServiceImpl.class);

	@Autowired
	private FundTransferDao fundTransferDao;
	@Autowired
	private MessageSource messageSource;

	@Transactional(rollbackFor = { FundTransferException.class })
	public FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest) {
		FundTransferResponse fundTransferResponse = new FundTransferResponse();
		boolean accountValidation = fundTransferDao
				.accountValidation(fundTransferRequest.getBeneficiaryAccountNumber());
		if (!accountValidation) {
			String responseCode = messageSource.getMessage("accountvalidation.001", null, null);
			String responseMessage = messageSource.getMessage(responseCode, null, null);
			throw new FundTransferException(responseCode, responseMessage, UUID.randomUUID().toString(), null, null,"500", fundTransferRequest);
		}
		boolean amountCheck = fundTransferDao.debitCheck(fundTransferRequest.getAmount(),
				fundTransferRequest.getBeneficiaryAccountNumber());
		LOGGER.info("AmountCheck{}", amountCheck);
		if (!amountCheck) {
			String responseCode = messageSource.getMessage("amountValidation.001", null, null);
			String responseMessage = messageSource.getMessage(responseCode, null, null);
			throw new FundTransferException(responseCode, responseMessage, UUID.randomUUID().toString(), null, null,"500", fundTransferRequest);
		}

		boolean result = false;
		if (NPCIConstants.CREDIT.equals(fundTransferRequest.getTransferType())) {
			result = fundTransferDao.creditFundTransfer(fundTransferRequest);
		} else {
			result = fundTransferDao.debitFundTransfer(fundTransferRequest);
		}
		if (result) {
			fundTransferResponse.setResponseCode(messageSource.getMessage(fundTransferRequest.getTransferType() + ".000", null, "000", null));
			fundTransferResponse.setResponseMessage(messageSource.getMessage(fundTransferRequest.getTransferType() + ".response." + fundTransferResponse.getResponseCode(), null,
					null));
		} else {
			fundTransferResponse.setResponseCode(messageSource.getMessage(fundTransferRequest.getTransferType() + ".001", null, "001", null));
			fundTransferResponse.setResponseMessage(messageSource.getMessage(fundTransferResponse.getResponseCode(), null, null));
		}
		return fundTransferResponse;
	}

	@Transactional(rollbackFor = { FundTransferException.class })
	public FundTransferResponse inwardFundTransfer(FundTransferRequest fundTransferRequest) {
		FundTransferResponse fundTransferResponse = new FundTransferResponse();
		boolean accountValidation = fundTransferDao.inwardAccountValidation(fundTransferRequest.getAccountNumber());
		if (!accountValidation) {
			String responseCode = messageSource.getMessage("accountvalidation.001", null, null);
			String responseMessage = messageSource.getMessage(responseCode, null, null);
			throw new FundTransferException(responseCode, responseMessage, UUID.randomUUID().toString(), null, null,"500", fundTransferRequest);
		}
		
	boolean amountCheck = fundTransferDao.inwardDebitCheck(fundTransferRequest.getAmount(),fundTransferRequest.getAccountNumber());
	LOGGER.info("AmountCheck{}", amountCheck);
	if (!amountCheck) {
		 String responseCode = messageSource.getMessage("amountValidation.001", null, null);
		String responseMessage = messageSource.getMessage(responseCode, null, null);
		throw new FundTransferException(responseCode, responseMessage, UUID.randomUUID().toString(), null, null,"500", fundTransferRequest);
	}

	boolean result = false;
	if (NPCIConstants.CREDIT.equals(fundTransferRequest.getTransferType())) {
		result = fundTransferDao.creditFundTransfer(fundTransferRequest);
	} else {
		result = fundTransferDao.inwardDebitFundTransfer(fundTransferRequest);
	}
	if(!result) {
		String responseCode=messageSource.getMessage("debit.response.001", null, null);
		String responseMessage=messageSource.getMessage(responseCode, null,null);

		throw new FundTransferException(responseCode,responseMessage,UUID.randomUUID().toString(),null,null,"500", fundTransferRequest);
	}
		fundTransferResponse.setResponseCode(messageSource.getMessage("inward.debit", null,"000", null));
        fundTransferResponse.setResponseMessage(messageSource.getMessage("inwarddebit.response." + fundTransferResponse.getResponseCode(), null, null));
	return fundTransferResponse;
}
	

}
