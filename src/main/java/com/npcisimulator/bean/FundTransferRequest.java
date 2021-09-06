package com.npcisimulator.bean;

public class FundTransferRequest {

	public String accountNumber;
	public String beneficiaryName;
	public String beneficiaryAccountNumber;
	public String beneficiaryIFSC;
	public String amount;
	public String remarks;
	public String transferType;
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getBeneficiaryAccountNumber() {
		return beneficiaryAccountNumber;
	}
	public void setBeneficiaryAccountNumber(String beneficiaryAccountNumber) {
		this.beneficiaryAccountNumber = beneficiaryAccountNumber;
	}
	public String getBeneficiaryIFSC() {
		return beneficiaryIFSC;
	}
	public void setBeneficiaryIFSC(String beneficiaryIFSC) {
		this.beneficiaryIFSC = beneficiaryIFSC;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getTransferType() {
		return transferType;
	}
	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}
	
}
