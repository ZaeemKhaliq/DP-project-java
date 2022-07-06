package com.example.dpproject.Entities.BankCard;

import com.example.dpproject.SingletonPattern.Account;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class GoldDebitCard extends DebitCard {

    private double debitLimit = 25000;

    public GoldDebitCard(Account account){
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

    public void checkDebit(){
        System.out.println("Checking debit...");

        double debit = this.account.getBalance();

        System.out.println("Your debit is: " + debit);
    }

    public void checkDebitLimit(){
        System.out.println("Your debit limit is: " + this.debitLimit);
    }

    public HashMap<String, String> getCardDetails(){
        HashMap<String, String> cardDetails = new HashMap<String, String>();

        String cardHolderName = this.cardHolderName;
        String cardNumber = this.cardNumber.toString();
        String cardExpiryDate = this.expiryDate;
        String debitLimit = Double.toString(this.debitLimit);

        cardDetails.put("cardType","Debit Gold");
        cardDetails.put("cardHolder",cardHolderName);
        cardDetails.put("cardNumber",cardNumber);
        cardDetails.put("expiryDate",cardExpiryDate);
        cardDetails.put("creditLimit",debitLimit);

        return cardDetails;

    }
}
