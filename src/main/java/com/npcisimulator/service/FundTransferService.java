package com.npcisimulator.service;

import com.npcisimulator.bean.FundTransferRequest;
import com.npcisimulator.bean.FundTransferResponse;

public interface FundTransferService {
FundTransferResponse fundTransfer(FundTransferRequest fundTransferRequest);
FundTransferResponse inwardFundTransfer(FundTransferRequest fundTransferRequest);
}
