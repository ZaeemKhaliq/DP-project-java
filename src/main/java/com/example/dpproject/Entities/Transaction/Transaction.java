package com.example.dpproject.Entities.Transaction;

import com.example.dpproject.StrategyPattern.CurrencyContext;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

public class Transaction {
    private UUID transactionId;
    private LocalDateTime transactionDate;
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;
    private String transactionCurrencyUnit;
    private double taxRate;
    private double finalAmount;

    public Transaction(String senderAccountNumber, String receiverAccountNumber,
                       int amount){
        this.transactionId = UUID.randomUUID();
        this.transactionDate = LocalDateTime.now();
        this.senderAccountNumber = senderAccountNumber;
        this.receiverAccountNumber = receiverAccountNumber;
        this.amount = amount;
        this.transactionCurrencyUnit = CurrencyContext.getCurrencyUnit();
        this.taxRate = CurrencyContext.getTaxRate();
        this.finalAmount = amount - (amount * this.taxRate);
    }

    public HashMap<String, String> getTransactionDetails(){
        HashMap<String, String> details = new HashMap<String, String>();

        String tId = this.transactionId.toString();
        String tDate = this.transactionDate.toString();
        String senderAccountNumber = this.senderAccountNumber;
        String receiverAccountNumber = this.receiverAccountNumber;
        String amount = Double.toString(this.amount);
        String transactionCurrencyUnit = this.transactionCurrencyUnit;
        String taxRate = Double.toString(this.taxRate);
        String finalAmount = Double.toString(this.finalAmount);

        details.put("transactionId",tId);
        details.put("transactionDate",tDate);
        details.put("senderAccountNumber",senderAccountNumber);
        details.put("receiverAccountNumber",receiverAccountNumber);
        details.put("amount",amount);
        details.put("transactionCurrencyUnit",transactionCurrencyUnit);
        details.put("taxRate",taxRate);
        details.put("finalAmount",finalAmount);

        return details;
    }
}
