package com.example.dpproject.TemplatePattern;

import com.example.dpproject.Entities.AccountForm.AccountForm;

public class YoungSaverAccountTemplate extends AccountTemplate {

    public YoungSaverAccountTemplate(AccountForm accountForm){
        this.accountForm = accountForm;
    }

    public void setAccountType(){
        String youngSaverAccount = this.accountTypes[0];
        this.templateAccount.setAccountType(youngSaverAccount);
    }

    public void setBalance(){
        this.templateAccount.setBalance(20);
    }
}
