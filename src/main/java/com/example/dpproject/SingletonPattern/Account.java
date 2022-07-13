package com.example.dpproject.SingletonPattern;

import com.example.dpproject.Entities.BankCard.CreditCard;
import com.example.dpproject.Entities.BankCard.DebitCard;
import com.example.dpproject.Entities.Transaction.Transaction;
import com.example.dpproject.ObserverPattern.Publisher;
import com.example.dpproject.ObserverPattern.SmsSubscriber;
import com.example.dpproject.ObserverPattern.Subscriber;
import com.example.dpproject.StrategyPattern.CurrencyContext;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class Account {
    private static Account instance = null;
    private String accountType;
    private String name;
    private String fatherName;
    private String address;
    private String cnicNum;
    private int age;
    private UUID accountNumber;
    private double balance;
    private String currencyUnit;
    private List<Transaction> transactions = new ArrayList<>();
    private Publisher publisher = new Publisher();
    private CreditCard creditCard;
    private DebitCard debitCard;

    private Account(){}

    public static Account getAccountInstance(){
        if(instance == null){
            instance = new Account();
        }

        return instance;
    }

    public static boolean checkInstance(){
        if(instance == null){
            return false;
        }

        return true;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setFatherName(String fatherName){
        this.fatherName = fatherName;
    }
    public String getFatherName(){
        return this.fatherName;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }

    public void setCnicNum(String cnicNum){
        this.cnicNum = cnicNum;
    }
    public String getCnicNum(){
        return this.cnicNum;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

    public void setAccountType(String accountType){
        this.accountType = accountType;
    }
    public String getAccountType(){
        return this.accountType;
    }

    public void setAccountNumber(UUID accountNumber){
        this.accountNumber = accountNumber;
    }
    public UUID getAccountNumber(){
        return this.accountNumber;
    }

    public void setBalance(double balance){
        this.balance = balance;
    }
    public double getBalance(){
        return this.balance;
    }
    public void changeBalanceCurrencyType(){
        double currentBalance = this.balance;

        String currentCurrencyUnit = this.currencyUnit;
        String newCurrencyUnit = CurrencyContext.getCurrencyUnit();

        String CurrencyPair = currentCurrencyUnit + "_" + newCurrencyUnit;

        double conversionFactor = CurrencyContext.currencyConversionRates.get(CurrencyPair);

        double newBalance = new BigDecimal( currentBalance * conversionFactor).setScale(2, RoundingMode.DOWN).doubleValue();

        this.balance = newBalance;
        this.currencyUnit = CurrencyContext.getCurrencyUnit();
    }

    public void setCurrencyUnit(String currencyUnit){
        this.currencyUnit = currencyUnit;
    }
    public String getCurrencyUnit(){
        return this.currencyUnit;
    }

    public void addSubscriber(String eventType,Subscriber subscriber){
        publisher.subscribe(eventType,subscriber);
    }
    public void removeSubscriber(String eventType, Subscriber subscriber){
        publisher.unsubscribe(eventType, subscriber);
    }
    public Subscriber checkSubscriber(String eventType){
        return this.publisher.getSubscriber(eventType);
    }

    public List<String> addTransaction(Transaction transaction){
        transactions.add(transaction);

        return publisher.notifyAllSubscribers("Transaction successful!");
    }

    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
    }
    public List<Transaction> getTransactions(){
        return this.transactions;
    }

    public void setPublisher(Publisher publisher){
        this.publisher = publisher;
    }

    public HashMap<String, Object> getSubscriptions(){
        class Subscription {
            private String value;
            private boolean isSubscribed;

            public Subscription(String value, boolean isSubscribed){
                this.value = value;
                this.isSubscribed = isSubscribed;
            }
            public String getValue(){
                return this.value;
            }

            public boolean getIsSubscribed(){
                return this.isSubscribed;
            }
        }

        HashMap<String, Object> areSubscribed = new HashMap<String, Object>();
        HashMap<String, Subscriber> subscribers = this.publisher.getSubscribers();

        Subscription subscription;

        if(!subscribers.containsKey("sms")){
            subscription = new Subscription("",false);

            areSubscribed.put("sms",subscription);
        }else{
            Subscriber subscriber = subscribers.get("sms");
            subscription = new Subscription(subscriber.getField(), true);

            areSubscribed.put("sms",subscription);
        }

        if(!subscribers.containsKey("email")){
            subscription = new Subscription("",false);

            areSubscribed.put("email",subscription);
        }else{
            Subscriber subscriber = subscribers.get("email");
            subscription = new Subscription(subscriber.getField(),true);

            areSubscribed.put("email",subscription);
        }

        return areSubscribed;
    }

    public void setCreditCard(CreditCard creditCard){
        this.creditCard = creditCard;
    }
    public CreditCard getCreditCard(){
        return this.creditCard;
    }

    public void setDebitCard(DebitCard debitCard){
        this.debitCard = debitCard;
    }
    public DebitCard getDebitCard(){
        return this.debitCard;
    }
}
