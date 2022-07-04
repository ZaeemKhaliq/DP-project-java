package com.example.dpproject.SingletonPattern;

import com.example.dpproject.Entities.Transaction.Transaction;
import com.example.dpproject.ObserverPattern.Publisher;
import com.example.dpproject.ObserverPattern.Subscriber;
import com.example.dpproject.StrategyPattern.CurrencyContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Account {
    private static Account instance = null;
    private String accountType;
    private String name;
    private String fatherName;
    private String address;
    private String cnicNum;
    private int age;
    private UUID accountNumber;
    private double balance;
    private String currencyUnit;

    private List<Transaction> transactions = new ArrayList<>();
    private Publisher publisher = new Publisher();

    private Account(){
        this.accountNumber = UUID.randomUUID();
        this.balance = 5;
        this.currencyUnit = CurrencyContext.getCurrencyUnit();
    }

    public static Account getAccountInstance(){
        if(instance == null){
            instance = new Account();
        }

        return instance;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setFatherName(String fatherName){
        this.fatherName = fatherName;
    }
    public String getFatherName(){
        return this.fatherName;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }

    public void setCnicNum(String cnicNum){
        this.cnicNum = cnicNum;
    }
    public String getCnicNum(){
        return this.cnicNum;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    public String getAccountType(){
        return this.accountType;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return this.balance;
    }
    public void changeBalanceCurrencyType(){
        double currentBalance = this.balance;

        String currentCurrencyUnit = this.currencyUnit;
        String newCurrencyUnit = CurrencyContext.getCurrencyUnit();

        String CurrencyPair = currentCurrencyUnit + "_" + newCurrencyUnit;

        double conversionFactor = CurrencyContext.currencyConversionRates.get(CurrencyPair);

        double newBalance = currentBalance * conversionFactor;

        this.balance = newBalance;
        this.currencyUnit = CurrencyContext.getCurrencyUnit();
    }

    public UUID getAccountNumber(){
        return this.accountNumber;
    }

    public void addSubscriber(String eventType,Subscriber subscriber){
        publisher.subscribe(eventType,subscriber);
    }
    public void removeSubscriber(String eventType, Subscriber subscriber){
        publisher.unsubscribe(eventType, subscriber);
    }

    public void addTransaction(Transaction transaction){
        try{
            transactions.add(transaction);

            publisher.notifyAllSubscribers("Transaction successful!");
        }
        catch (Exception e){
            publisher.notifyAllSubscribers("Transaction unsuccessful!");
        }

    }
    public List<Transaction> getTransactions(){
        return this.transactions;
    }


}
