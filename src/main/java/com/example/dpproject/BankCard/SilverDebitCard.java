package com.example.dpproject.BankCard;

import com.example.dpproject.SingletonPattern.Account;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class SilverDebitCard extends DebitCard {
    private Account account;
    private String cardHolderName;
    private UUID cardNumber;
    private String expiryDate;
    private double debitLimit = 20000;

    public SilverDebitCard(Account account){
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

        cardDetails.put("Card Holder",cardHolderName);
        cardDetails.put("Card Number",cardNumber);
        cardDetails.put("Expiry Date",cardExpiryDate);
        cardDetails.put("Debit Limit",debitLimit);

        return cardDetails;

    }
}
