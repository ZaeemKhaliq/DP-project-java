package com.example.dpproject.BuilderPattern;

import com.example.dpproject.Account.Account;

public class AccountBuilder {
    private Account account;

    public AccountBuilder(){
        this.account = new Account();
    }

    public void setName(String name){
        account.setName(name);
    }

    public void setFatherName(String fatherName){
        account.setFatherName(fatherName);
    }

    public void setAddress(String address){
        account.setAddress(address);
    }

    public void setCnicNum(String cnicNum){
        account.setCnicNum(cnicNum);
    }

    public void setAge(int age){
        account.setAge(age);
    }

    public Account getAccount(){
        return this.account;
    }
}
