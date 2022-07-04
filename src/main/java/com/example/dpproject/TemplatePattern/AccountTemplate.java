package com.example.dpproject.TemplatePattern;

import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.Entities.AccountForm.AccountForm;
import com.example.dpproject.Constants.AccountTypes;

public abstract class AccountTemplate {
    protected AccountForm accountForm;
    protected Account templateAccount = Account.getAccountInstance();
    protected String[] accountTypes = AccountTypes.accountTypes;

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

}
