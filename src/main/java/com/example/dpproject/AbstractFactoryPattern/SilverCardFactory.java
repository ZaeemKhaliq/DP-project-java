package com.example.dpproject.AbstractFactoryPattern;

import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.BankCard.*;

public class SilverCardFactory implements BankCardFactory {
    public CreditCard createCreditCard(Account account){
        CreditCard silverCreditCard = new SilverCreditCard(account);

        return silverCreditCard;
    }

    public DebitCard createDebitCard(Account account){
        DebitCard silverDebitCard = new SilverDebitCard(account);

        return silverDebitCard;
    }

}
