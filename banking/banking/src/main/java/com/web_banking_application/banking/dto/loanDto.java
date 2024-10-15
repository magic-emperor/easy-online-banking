package com.web_banking_application.banking.dto;

public class loanDto {
	private long loanId;
	private String userName;
	private String userAddress;
	private String loanType;
	private int amountRequired;
	private long accountNumber;
	private long mobileNumber;
	private String loanStatus;
	private long userId;
	public loanDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public loanDto(long loanId, String userName, String userAddress, String loanType, int amountRequired,
			long accountNumber, long mobileNumber, String loanStatus, long userId) {
		super();
		this.loanId = loanId;
		this.userName = userName;
		this.userAddress = userAddress;
		this.loanType = loanType;
		this.amountRequired = amountRequired;
		this.accountNumber = accountNumber;
		this.mobileNumber = mobileNumber;
		this.loanStatus = loanStatus;
		this.userId = userId;
	}
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserAddress() {
		return userAddress;
	}
	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public int getAmountRequired() {
		return amountRequired;
	}
	public void setAmountRequired(int amountRequired) {
		this.amountRequired = amountRequired;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	
}
