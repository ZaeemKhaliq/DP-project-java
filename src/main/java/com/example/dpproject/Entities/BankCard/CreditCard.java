package com.example.dpproject.Entities.BankCard;

import com.example.dpproject.SingletonPattern.Account;

import java.util.HashMap;
import java.util.UUID;

public abstract class CreditCard {
    protected Account account;
    protected String cardHolderName;
    protected UUID cardNumber;
    protected String expiryDate;

    public abstract void checkCredit();
    public abstract void checkCreditLimit();
    public abstract HashMap<String, String> getCardDetails();
}
