package com.example.dpproject.AbstractFactoryPattern;

import com.example.dpproject.Entities.BankCard.CreditCard;
import com.example.dpproject.Entities.BankCard.DebitCard;
import com.example.dpproject.Entities.BankCard.SilverCreditCard;
import com.example.dpproject.Entities.BankCard.SilverDebitCard;
import com.example.dpproject.SingletonPattern.Account;

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
