package com.example.dpproject.TemplatePattern;

import com.example.dpproject.ObserverPattern.Publisher;
import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.Entities.AccountForm.AccountForm;
import com.example.dpproject.Constants.AccountTypes;
import com.example.dpproject.StrategyPattern.CurrencyContext;

import java.util.ArrayList;
import java.util.UUID;

public abstract class AccountTemplate {
    protected AccountForm accountForm;
    protected Account templateAccount = Account.getAccountInstance();
    protected String[] accountTypes = AccountTypes.accountTypes;

    public Account createAccount(){
        initializeAccount();

        setName();
        setFatherName();
        setAddress();
        setCnicNum();
        setAge();

        setAccountType();
        setBalance();

        return this.templateAccount;
    };

    public void initializeAccount(){
        this.templateAccount.setAccountNumber(UUID.randomUUID());
        this.templateAccount.setBalance(0);
        this.templateAccount.setCurrencyUnit(CurrencyContext.getCurrencyUnit());
        this.templateAccount.setTransactions(new ArrayList<>());
        this.templateAccount.setPublisher(new Publisher());
        this.templateAccount.setCreditCard(null);
        this.templateAccount.setDebitCard(null);
    }

    public void setName(){
        this.templateAccount.setName(this.accountForm.getName());
    }

    public void setFatherName(){
        this.templateAccount.setFatherName(this.accountForm.getFatherName());
    }

    public void setAddress(){
        this.templateAccount.setAddress(this.accountForm.getAddress());
    }

    public void setCnicNum(){
        this.templateAccount.setCnicNum(this.accountForm.getCnicNum());
    }

    public void setAge(){
        this.templateAccount.setAge(this.accountForm.getAge());
    }

    public abstract void setAccountType();
    public abstract void setBalance();

}
