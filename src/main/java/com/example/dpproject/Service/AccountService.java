package com.example.dpproject.Service;

import com.example.dpproject.BuilderPattern.AccountBuilder;
import com.example.dpproject.Entities.AccountForm.AccountForm;
import com.example.dpproject.Entities.BankCard.*;
import com.example.dpproject.Entities.Transaction.Transaction;
import com.example.dpproject.ObserverPattern.EmailSubscriber;
import com.example.dpproject.ObserverPattern.SmsSubscriber;
import com.example.dpproject.ObserverPattern.Subscriber;
import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.StrategyPattern.CurrencyContext;
import com.example.dpproject.StrategyPattern.EURCurrencyTypeStrategy;
import com.example.dpproject.StrategyPattern.PKRCurrencyTypeStrategy;
import com.example.dpproject.StrategyPattern.USDCurrencyTypeStrategy;
import com.example.dpproject.TemplatePattern.CurrentAccountTemplate;
import com.example.dpproject.TemplatePattern.YoungSaverAccountTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class AccountService {
    private AccountBuilder accountBuilder;
    private Account account;

    public void createAccountBuilder(){
        this.accountBuilder = new AccountBuilder();
    }

    public void setName(String name){
        this.accountBuilder.setName(name);
    }

    public void setFatherName(String fatherName){
        this.accountBuilder.setFatherName(fatherName);
    }

    public void setAddress(String address){
        this.accountBuilder.setAddress(address);
    }

    public void setCnicNum(String cnicNum){
        this.accountBuilder.setCnicNum(cnicNum);
    }

    public void setAge(int age){
        this.accountBuilder.setAge(age);
    }

    public AccountForm getAccountForm(){
        return this.accountBuilder.getAccount();
    }

    public ResponseEntity<String> createAccount(String accountType, AccountForm accountForm){

        if(Objects.equals(accountType, "youngSaver")){
            YoungSaverAccountTemplate youngSaverAccountTemplate = new YoungSaverAccountTemplate(accountForm);
            Account newAccount = youngSaverAccountTemplate.createAccount();

            return ResponseEntity.status(HttpStatus.OK).body("Your young saver account is created successfully!");
        }

        if(Objects.equals(accountType, "current")){
            CurrentAccountTemplate currentAccountTemplate = new CurrentAccountTemplate(accountForm);
            Account newAccount = currentAccountTemplate.createAccount();

            return ResponseEntity.status(HttpStatus.OK).body("Your current account is created successfully!");
        }

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body("Oops. Unknown error. No account was created.");

    }

    public Account getAccount(){
        this.account = Account.getAccountInstance();

        return this.account;
    }

    public ResponseEntity<String> addBalance(double balance){
        Account account = Account.getAccountInstance();
        double currentBalance = account.getBalance();
        double newBalance = new BigDecimal(currentBalance + balance).setScale(2, RoundingMode.DOWN).doubleValue();

        account.setBalance(newBalance);

        return ResponseEntity.status(HttpStatus.OK).body("Balance added successfully!\nYour new balance is: "+newBalance);
    }

    public HashMap<String, String> getCurrencyContext(){
        String currencyUnit = CurrencyContext.getCurrencyUnit();

        HashMap<String, String> currencyContext = new HashMap<String, String>();

        DecimalFormat df = new DecimalFormat("0.00");

        currencyContext.put("taxRate",df.format(CurrencyContext.getTaxRate()));
        currencyContext.put("currencyUnit",currencyUnit);

        return currencyContext;
    }

    public List<HashMap<String, String>> getAllCurrencyContexts() {
        HashMap<String, String> currency1 = new HashMap<String, String>();
        HashMap<String, String> currency2 = new HashMap<String, String>();
        HashMap<String, String> currency3 = new HashMap<String, String>();

        EURCurrencyTypeStrategy eurCurrencyTypeStrategy = new EURCurrencyTypeStrategy();
        PKRCurrencyTypeStrategy pkrCurrencyTypeStrategy = new PKRCurrencyTypeStrategy();
        USDCurrencyTypeStrategy usdCurrencyTypeStrategy = new USDCurrencyTypeStrategy();

        currency1.put("taxRate",Double.toString(eurCurrencyTypeStrategy.getTaxRate()));
        currency2.put("taxRate",Double.toString(pkrCurrencyTypeStrategy.getTaxRate()));
        currency3.put("taxRate",Double.toString(usdCurrencyTypeStrategy.getTaxRate()));

        currency1.put("currencyUnit",eurCurrencyTypeStrategy.getCurrencyUnit());
        currency2.put("currencyUnit",pkrCurrencyTypeStrategy.getCurrencyUnit());
        currency3.put("currencyUnit",usdCurrencyTypeStrategy.getCurrencyUnit());

        List<HashMap<String, String>> allCurrencies = new ArrayList<HashMap<String,String>>();
        allCurrencies.add(currency1);
        allCurrencies.add(currency2);
        allCurrencies.add(currency3);

        return allCurrencies;
    }

    public ResponseEntity<String> changeCurrencyUnit(String currencyUnit){
        if(Objects.equals(currencyUnit,"eur")){
            EURCurrencyTypeStrategy eurCurrencyTypeStrategy = new EURCurrencyTypeStrategy();
            CurrencyContext.setCurrencyTypeStrategy(eurCurrencyTypeStrategy);

            return ResponseEntity.status(HttpStatus.OK).body("Currency unit set to EUR successfully!");
        }

        if(Objects.equals(currencyUnit,"usd")){
            USDCurrencyTypeStrategy usdCurrencyTypeStrategy = new USDCurrencyTypeStrategy();
            CurrencyContext.setCurrencyTypeStrategy(usdCurrencyTypeStrategy);

            return ResponseEntity.status(HttpStatus.OK).body("Currency unit set to USD successfully!");
        }

        if(Objects.equals(currencyUnit,"pkr")){
            PKRCurrencyTypeStrategy pkrCurrencyTypeStrategy = new PKRCurrencyTypeStrategy();
            CurrencyContext.setCurrencyTypeStrategy(pkrCurrencyTypeStrategy);

            return ResponseEntity.status(HttpStatus.OK).body("Currency unit set to PKR successfully!");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ERROR. Currency unit not available.");

    }

    public String addSubscriber(String type, String value){
        Account account = Account.getAccountInstance();

        if(Objects.equals(type,"email")){
            EmailSubscriber emailSubscriber = new EmailSubscriber(value);

            account.addSubscriber("email",emailSubscriber);

            return "Email subscription added successfull!";
        }
        if(Objects.equals(type,"sms")){
            SmsSubscriber smsSubscriber = new SmsSubscriber(value);

            account.addSubscriber("sms",smsSubscriber);

            return "SMS subscription added successfull!";
        }

        return "Invalid subscription type";
    }

    public String removeSubscriber(String type){
        Account account = Account.getAccountInstance();
        Subscriber subscriber;

        if(Objects.equals(type,"sms")){
            subscriber = account.checkSubscriber(type);
            account.removeSubscriber(type,subscriber);

            return "SMS Subscription removed successfully!";
        }

        if(Objects.equals(type,"email")){
            subscriber = account.checkSubscriber(type);
            account.removeSubscriber(type,subscriber);

            return "Email Subscription removed successfully!";
        }

        return "Invalid subscription type";
    }

    public ResponseEntity<List<String>> addTransaction(Transaction transaction){
        Account account = Account.getAccountInstance();

        double currentBalance = account.getBalance();
        double newBalance = currentBalance - transaction.getAmount();

        List<String> messages;
        messages = account.addTransaction(transaction);
        account.setBalance(newBalance);

        String currencyUnit = CurrencyContext.getCurrencyUnit();

        List<String> responses = new ArrayList<>();
        String message = "Transaction successful!\n" +
                "Previous balance:" + currentBalance + " "+ currencyUnit + "\n" +
                "New balance: "+account.getBalance() + " " + currencyUnit;

        responses.add(message);
        for(int i = 0;i<messages.size();i++){
            responses.add(messages.get(i));
        }
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    public List<Transaction> getTransactions(){
        Account account = Account.getAccountInstance();

        List<Transaction> transactions = account.getTransactions();

        return transactions;
    }

    public HashMap<String, Object> getSubscriptions(){
        Account account = Account.getAccountInstance();


        return account.getSubscriptions();
    }

    public ResponseEntity<String> createCreditCard(String variant){
        Account account = Account.getAccountInstance();

        if(Objects.equals(variant,"silver")){
            CreditCard silverCreditCard = new SilverCreditCard(account);

            account.setCreditCard(silverCreditCard);
            return ResponseEntity.status(HttpStatus.OK).body("Your Silver Credit Card is created successfully!");
        }

        if(Objects.equals(variant,"gold")){
            CreditCard goldCreditCard = new GoldCreditCard(account);

            account.setCreditCard(goldCreditCard);

            return ResponseEntity.status(HttpStatus.OK).body("Your Gold Credit Card is created successfully!");

        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid card variant!");
    }

    public ResponseEntity<String> createDebitCard(String variant){
        Account account = Account.getAccountInstance();

        if(Objects.equals(variant,"silver")){
            DebitCard silverDebitCard = new SilverDebitCard(account);

            account.setDebitCard(silverDebitCard);

            return ResponseEntity.status(HttpStatus.OK).body("Your Silver Debit Card is created successfully!");
        }

        if(Objects.equals(variant,"gold")){
            DebitCard goldDebitCard = new GoldDebitCard(account);

            account.setDebitCard(goldDebitCard);

            return ResponseEntity.status(HttpStatus.OK).body("Your Gold Debit Card is created successfully!");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid card variant!");
    }

    public ResponseEntity<String> removeCreditCard(){
        Account account = Account.getAccountInstance();

        account.setCreditCard(null);

        return ResponseEntity.status(HttpStatus.OK).body("Credit card removed successfully!");
    }

    public ResponseEntity<String> removeDebitCard(){
        Account account = Account.getAccountInstance();

        account.setDebitCard(null);

        return ResponseEntity.status(HttpStatus.OK).body("Debit card removed successfully!");
    }

}
