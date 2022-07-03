package com.example.dpproject.TemplatePattern;

import com.example.dpproject.AccountForm.AccountForm;

public class CurrentAccountTemplate  extends AccountTemplate{
    public CurrentAccountTemplate(AccountForm accountForm){
        this.accountForm = accountForm;
    }

    public void setAccountType(){
        String currentAccount = this.accountTypes[1];
        this.templateAccount.setAccountType(currentAccount);
    }
}
