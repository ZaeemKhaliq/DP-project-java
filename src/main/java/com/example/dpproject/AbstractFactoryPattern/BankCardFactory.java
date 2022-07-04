package com.example.dpproject.AbstractFactoryPattern;

import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.Entities.BankCard.CreditCard;
import com.example.dpproject.Entities.BankCard.DebitCard;

public interface BankCardFactory {
    public CreditCard createCreditCard(Account account);

    public DebitCard createDebitCard(Account account);
}
