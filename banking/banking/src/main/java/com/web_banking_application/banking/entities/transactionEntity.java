package com.web_banking_application.banking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "TransactionDetails")
public class transactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transaction_Id; // Change to Long to allow null

    @Column(name = "userId")
    private Long user_Id;

    @Column(name = "transaction_Date")
    private String transaction_Date;

    @Column(name = "transactionDetails")
    private String transaction_Details;

    @Column(name = "amountTransfered")
    private Long amount_transfered;

    @Column(name = "balance")
    private Long balance;

    public transactionEntity() {
        super();
    }

    public transactionEntity(Long transaction_Id, Long user_Id, String transaction_Date, String transaction_Details, Long amount_transfered, Long balance) {
        this.transaction_Id = transaction_Id;
        this.user_Id = user_Id;
        this.transaction_Date = transaction_Date;
        this.transaction_Details = transaction_Details;
        this.amount_transfered = amount_transfered;
        this.balance = balance;
    }

    public Long getTransaction_Id() {
        return transaction_Id;
    }

    public void setTransaction_Id(Long transaction_Id) {
        this.transaction_Id = transaction_Id;
    }

    public Long getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Long user_Id) {
        this.user_Id = user_Id;
    }

    public String getTransaction_Date() {
        return transaction_Date;
    }

    public void setTransaction_Date(String transaction_Date) {
        this.transaction_Date = transaction_Date;
    }

    public String getTransaction_Details() {
        return transaction_Details;
    }

    public void setTransaction_Details(String transaction_Details) {
        this.transaction_Details = transaction_Details;
    }

    public Long getAmount_transfered() {
        return amount_transfered;
    }

    public void setAmount_transfered(Long amount_transfered) {
        this.amount_transfered = amount_transfered;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

}
