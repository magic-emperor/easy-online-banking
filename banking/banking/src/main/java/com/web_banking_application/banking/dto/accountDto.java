package com.web_banking_application.banking.dto;

public class accountDto {
    private Long accId;
    private Long userId;
    private Long accNumber;
    private double accBalance;
    private String accCreatedAt;
    private String accType;

    // Default constructor


    // Getters and setters
    public Long getAccId() {
        return accId;
    }

    public accountDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public accountDto(Long accId, Long userId, Long accNumber, double accBalance, String accCreatedAt,
			String accType) {
		super();
		this.accId = accId;
		this.userId = userId;
		this.accNumber = accNumber;
		this.accBalance = accBalance;
		this.accCreatedAt = accCreatedAt;
		this.accType = accType;
	}

	public void setAccId(long accId) {
        this.accId = accId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getAccNumber() {
        return accNumber;
    }

    public void setAccNumber(Long accNumber) {
        this.accNumber = accNumber;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double d) {
        this.accBalance = d;
    }

    public String getAccCreatedAt() {
        return accCreatedAt;
    }

    public void setAccCreatedAt(String accCreatedAt) {
        this.accCreatedAt = accCreatedAt;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }
    @Override
    public String toString() {
        return "accountEntity{" +
                "accId=" + accId +
                ", userId=" + userId +
                ", accNumber=" + accNumber +
                ", accBalance=" + accBalance +
                ", accCreatedDate='" + accCreatedAt + '\'' +
                ", accType='" + accType + '\'' +
                '}';
    }
}