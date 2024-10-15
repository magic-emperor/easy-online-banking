package com.web_banking_application.banking.dto;

public class transactionDto {
	private Long transactionID;
	private Long userId;
	private String transferDate;
	private String transferDetails;
	private Long amountTransfered;
	private Long balance;
	public transactionDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public transactionDto(Long transactionID, Long userId, String transferDate, String transferDetails,
			Long amountTransfered, Long balance) {
		super();
		this.transactionID = transactionID;
		this.userId = userId;
		this.transferDate = transferDate;
		this.transferDetails = transferDetails;
		this.amountTransfered = amountTransfered;
		this.balance = balance;
	}


	public Long getTransactionID() {
		return transactionID;
	}
	public void setTransactionID(Long transactionID) {
		this.transactionID = transactionID;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getTransferDate() {
		return transferDate;
	}
	public void setTransferDate(String transferDate) {
		this.transferDate = transferDate;
	}
	public String getTransferDetails() {
		return transferDetails;
	}
	public void setTransferDetails(String transferDetails) {
		this.transferDetails = transferDetails;
	}
	public Long getAmountTransfered() {
		return amountTransfered;
	}
	public void setAmountTransfered(Long amountTransfered) {
		this.amountTransfered = amountTransfered;
	}
	public Long getBalance() {
		return balance;
	}
	public void setBalance(Long balance) {
		this.balance = balance;
	}
	
}
