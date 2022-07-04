package com.example.dpproject.BuilderPattern;

import com.example.dpproject.Entities.AccountForm.AccountForm;

public class AccountBuilder {
    private AccountForm accountForm;

    public AccountBuilder(){
        this.accountForm = new AccountForm();
    }

    public void setName(String name){
        accountForm.setName(name);
    }

    public void setFatherName(String fatherName){
        accountForm.setFatherName(fatherName);
    }

    public void setAddress(String address){
        accountForm.setAddress(address);
    }

    public void setCnicNum(String cnicNum){
        accountForm.setCnicNum(cnicNum);
    }

    public void setAge(int age){
        accountForm.setAge(age);
    }

    public AccountForm getAccount(){
        return this.accountForm;
    }
}
