package com.example.dpproject.Entities.BankCard;

import com.example.dpproject.SingletonPattern.Account;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.UUID;

public class SilverCreditCard extends CreditCard {
    private Account account;
    private String cardHolderName;
    private UUID cardNumber;
    private String expiryDate;
    private double creditLimit = 20000;

    public SilverCreditCard(Account account){
        this.account = account;
        this.cardHolderName = account.getName();
        this.cardNumber = UUID.randomUUID();
        this.setCardExpiryDate();
    }

    public void setCardExpiryDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM");
        Calendar cal = Calendar.getInstance();

        int min = 30;
        int max=60;
        int days = (int)Math.floor(Math.random()*(max-min+1)+min);

        cal.add(Calendar.DAY_OF_MONTH,days);

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

        cardDetails.put("cardType","Credit");
        cardDetails.put("cardVariant","Silver");
        cardDetails.put("cardHolder",cardHolderName);
        cardDetails.put("cardNumber",cardNumber);
        cardDetails.put("expiryDate",cardExpiryDate);
        cardDetails.put("creditLimit",creditLimit);

        return cardDetails;

    }
}
