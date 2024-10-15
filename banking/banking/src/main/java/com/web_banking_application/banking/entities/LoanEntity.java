package com.web_banking_application.banking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Loan")
public class LoanEntity {
	@Column(name = "loanId")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long loan_Id;
	@Column(name = "userName")
	private String user_Name;
	@Column(name = "address")
	private String user_Address;
	@Column(name = "loanType")
	private String loan_Type;
	@Column(name = "amountRequired")
	private int amount_Required;
	@Column(name = "accountNumber")
	private long account_Number;
	@Column(name = "mobileNumber")
	private long mobile_Number;
	@Column(name = "loanStatus")
	private String loan_Status;
	@Column(name = "userId")
	private long user_Id;
	public LoanEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LoanEntity(long loan_Id, String user_Name, String user_Address, String loan_Type, int amount_Required,
			long account_Number, long mobile_Number, String loan_Status, long user_Id) {
		super();
		this.loan_Id = loan_Id;
		this.user_Name = user_Name;
		this.user_Address = user_Address;
		this.loan_Type = loan_Type;
		this.amount_Required = amount_Required;
		this.account_Number = account_Number;
		this.mobile_Number = mobile_Number;
		this.loan_Status = loan_Status;
		this.user_Id = user_Id;
	}
	public long getLoan_Id() {
		return loan_Id;
	}
	public void setLoan_Id(long loan_Id) {
		this.loan_Id = loan_Id;
	}
	public String getUser_Name() {
		return user_Name;
	}
	public void setUser_Name(String user_Name) {
		this.user_Name = user_Name;
	}
	public String getUser_Address() {
		return user_Address;
	}
	public void setUser_Address(String user_Address) {
		this.user_Address = user_Address;
	}
	public String getLoan_Type() {
		return loan_Type;
	}
	public void setLoan_Type(String loan_Type) {
		this.loan_Type = loan_Type;
	}
	public int getAmount_Required() {
		return amount_Required;
	}
	public void setAmount_Required(int amount_Required) {
		this.amount_Required = amount_Required;
	}
	public long getAccount_Number() {
		return account_Number;
	}
	public void setAccount_Number(long account_Number) {
		this.account_Number = account_Number;
	}
	public long getMobile_Number() {
		return mobile_Number;
	}
	public void setMobile_Number(long mobile_Number) {
		this.mobile_Number = mobile_Number;
	}
	public String getLoan_Status() {
		return loan_Status;
	}
	public void setLoan_Status(String loan_Status) {
		this.loan_Status = loan_Status;
	}
	public long getUser_Id() {
		return user_Id;
	}
	public void setUser_Id(long user_Id) {
		this.user_Id = user_Id;
	}
	
	
}
