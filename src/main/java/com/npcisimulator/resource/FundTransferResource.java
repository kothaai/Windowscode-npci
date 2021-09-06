package com.npcisimulator.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.npcisimulator.bean.FundTransferRequest;
import com.npcisimulator.bean.FundTransferResponse;
import com.npcisimulator.service.FundTransferService;

@RestController
public class FundTransferResource {
	@Autowired
	private FundTransferService fundTransferService;
	
	@RequestMapping("/fund/transfer")
	public FundTransferResponse fundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
	return fundTransferService.fundTransfer(fundTransferRequest);
	}	

	@RequestMapping("/debitFund/transfer")
	public FundTransferResponse inwardFundTransfer(@RequestBody FundTransferRequest fundTransferRequest) {
	return fundTransferService.inwardFundTransfer(fundTransferRequest);
	}	
	
}
