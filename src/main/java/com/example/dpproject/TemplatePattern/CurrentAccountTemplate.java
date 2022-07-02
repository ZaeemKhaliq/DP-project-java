package com.example.dpproject.TemplatePattern;

import com.example.dpproject.Account.Account;

public class CurrentAccountTemplate  extends AccountTemplate{
    public CurrentAccountTemplate(Account account){
        this.account = account;
    }

    public void setAccountType(){
        String currentAccount = this.accountTypes[1];
        this.templateAccount.setAccountType(currentAccount);
    }
}
