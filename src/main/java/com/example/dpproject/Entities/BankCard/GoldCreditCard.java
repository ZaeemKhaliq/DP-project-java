package com.example.dpproject.Entities.BankCard;

import com.example.dpproject.SingletonPattern.Account;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class GoldCreditCard extends CreditCard {
    private double creditLimit = 25000;

    public GoldCreditCard(Account account){
        this.account = account;
        this.cardHolderName = account.getName();
        this.cardNumber = UUID.randomUUID();
        this.setCardExpiryDate();
    }

    public void setCardExpiryDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH,30);

        String cardExpiryDate = sdf.format(cal.getTime());

        this.expiryDate = cardExpiryDate;
    }

    public void checkCredit(){
        System.out.println("Checking credit...");

        double credit = this.account.getBalance();

        System.out.println("Your credit is: " + credit);
    }

    public void checkCreditLimit(){
        System.out.println("Your credit limit is: " + this.creditLimit);
    }

    public HashMap<String, String> getCardDetails(){
        HashMap<String, String> cardDetails = new HashMap<String, String>();

        String cardHolderName = this.cardHolderName;
        String cardNumber = this.cardNumber.toString();
        String cardExpiryDate = this.expiryDate;
        String creditLimit = Double.toString(this.creditLimit);

        cardDetails.put("Card Holder",cardHolderName);
        cardDetails.put("Card Number",cardNumber);
        cardDetails.put("Expiry Date",cardExpiryDate);
        cardDetails.put("Credit Limit",creditLimit);

        return cardDetails;

    }
}
