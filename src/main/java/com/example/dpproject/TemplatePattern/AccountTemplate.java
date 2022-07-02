package com.example.dpproject.TemplatePattern;

import com.example.dpproject.Account.Account;
import com.example.dpproject.SingletonPattern.AccountTypes;

public abstract class AccountTemplate {
    protected Account account;
    protected Account templateAccount;
    protected String[] accountTypes = AccountTypes.getInstance().getAccountTypes();

    public Account createAccount(){
        setName();
        setFatherName();
        setAddress();
        setCnicNum();
        setAge();

        setAccountType();

        return this.templateAccount;
    };

    public void setName(){
        this.templateAccount.setName(this.account.getName());
    }

    public void setFatherName(){
        this.templateAccount.setFatherName(this.account.getFatherName());
    }

    public void setAddress(){
        this.templateAccount.setAddress(this.account.getAddress());
    }

    public void setCnicNum(){
        this.templateAccount.setCnicNum(this.account.getCnicNum());
    }

    public void setAge(){
        this.templateAccount.setAge(this.account.getAge());
    }

    public abstract void setAccountType();

}
