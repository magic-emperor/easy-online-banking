package com.web_banking_application.banking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "accountDetails")
public class accountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "acc_Id")
    private Long accId;

    @Column(name = "accountNumber")
    private Long accNumber;

    @Column(name ="userId")
    private Long userId;

    @Column(name = "accountBalance")
    private Double accBalance;

    @Column(name = "accountDate")
    private String accCreatedDate;

    @Column(name = "accType")
    private String accType;

    // Default constructor
    public accountEntity() {}

    // Full constructor
    public accountEntity(Long accId, Long userId, Long accNumber, Double accBalance, String accCreatedDate, String accType) {
        this.accId = accId;
        this.userId = userId;
        this.accNumber = accNumber;
        this.accBalance = accBalance;
        this.accCreatedDate = accCreatedDate;
        this.accType = accType;
    }

    // Getters and setters
    public Long getAccId() { return accId; }
    public void setAccId(Long accId) { this.accId = accId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getAccNumber() { return accNumber; }
    public void setAccNumber(Long accNumber) { this.accNumber = accNumber; }

    public Double getAccBalance() { return accBalance; }
    public void setAccBalance(Double accBalance) { this.accBalance = accBalance; }

    public String getAccCreatedDate() { return accCreatedDate; }
    public void setAccCreatedDate(String accCreatedDate) { this.accCreatedDate = accCreatedDate; }

    public String getAccType() { return accType; }
    public void setAccType(String accType) { this.accType = accType; }
    @Override
    public String toString() {
        return "accountEntity{" +
                "accId=" + accId +
                ", userId=" + userId +
                ", accNumber=" + accNumber +
                ", accBalance=" + accBalance +
                ", accCreatedDate='" + accCreatedDate + '\'' +
                ", accType='" + accType + '\'' +
                '}';
    }
}