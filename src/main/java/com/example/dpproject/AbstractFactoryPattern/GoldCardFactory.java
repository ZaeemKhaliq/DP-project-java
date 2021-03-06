package com.example.dpproject.AbstractFactoryPattern;

import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.Entities.BankCard.CreditCard;
import com.example.dpproject.Entities.BankCard.DebitCard;
import com.example.dpproject.Entities.BankCard.GoldCreditCard;
import com.example.dpproject.Entities.BankCard.GoldDebitCard;

public class GoldCardFactory implements BankCardFactory{
    public CreditCard createCreditCard(Account account){
        CreditCard goldCreditCard = new GoldCreditCard(account);

        return goldCreditCard;
    }

    public DebitCard createDebitCard(Account account){
        DebitCard goldDebitCard = new GoldDebitCard(account);

        return goldDebitCard;
    }
}
