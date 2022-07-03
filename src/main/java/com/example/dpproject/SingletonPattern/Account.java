package com.example.dpproject.SingletonPattern;

import java.util.UUID;

public class Account {
    private static Account instance = null;
    private String accountType;
    private String name;
    private String fatherName;
    private String address;
    private String cnicNum;
    private int age;
    private double balance;
    private UUID accountNumber;

    private Account(){
        this.balance = 0;
        this.accountNumber = UUID.randomUUID();
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

    public UUID getAccountNumber(){
        return this.accountNumber;
    }
}
