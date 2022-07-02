package com.example.dpproject.TemplatePattern;

import com.example.dpproject.Account.Account;

public class YoungSaverAccountTemplate extends AccountTemplate {

    public YoungSaverAccountTemplate(Account account){
        this.account = account;
    }

    public void setAccountType(){
        String youngSaverAccount = this.accountTypes[0];
        this.templateAccount.setAccountType(youngSaverAccount);
    }


}
