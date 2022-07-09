package com.example.dpproject.Service;

import com.example.dpproject.BuilderPattern.AccountBuilder;
import com.example.dpproject.Entities.AccountForm.AccountForm;
import com.example.dpproject.Entities.Transaction.Transaction;
import com.example.dpproject.ObserverPattern.EmailSubscriber;
import com.example.dpproject.ObserverPattern.SmsSubscriber;
import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.StrategyPattern.CurrencyContext;
import com.example.dpproject.StrategyPattern.EURCurrencyTypeStrategy;
import com.example.dpproject.StrategyPattern.PKRCurrencyTypeStrategy;
import com.example.dpproject.StrategyPattern.USDCurrencyTypeStrategy;
import com.example.dpproject.TemplatePattern.CurrentAccountTemplate;
import com.example.dpproject.TemplatePattern.YoungSaverAccountTemplate;
import org.springframework.stereotype.Service;

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

    public String createAccount(String accountType, AccountForm accountForm){

        if(Objects.equals(accountType, "youngSaver")){
            YoungSaverAccountTemplate youngSaverAccountTemplate = new YoungSaverAccountTemplate(accountForm);
            Account newAccount = youngSaverAccountTemplate.createAccount();

            return "Your young saver account is created successfully!";
        }

        if(Objects.equals(accountType, "current")){
            CurrentAccountTemplate currentAccountTemplate = new CurrentAccountTemplate(accountForm);
            Account newAccount = currentAccountTemplate.createAccount();

            return "Your current account is created successfully!";
        }

        return "Oops. Unknown error. No account was created.";
    }

    public Account getAccount(){
        this.account = Account.getAccountInstance();

        return this.account;
    }

    public String changeCurrencyUnit(String currencyUnit){
        if(Objects.equals(currencyUnit,"eur")){
            EURCurrencyTypeStrategy eurCurrencyTypeStrategy = new EURCurrencyTypeStrategy();
            CurrencyContext.setCurrencyTypeStrategy(eurCurrencyTypeStrategy);

            return "Currency unit set to EUR successfully!";
        }

        if(Objects.equals(currencyUnit,"usd")){
            USDCurrencyTypeStrategy usdCurrencyTypeStrategy = new USDCurrencyTypeStrategy();
            CurrencyContext.setCurrencyTypeStrategy(usdCurrencyTypeStrategy);

            return "Currency unit set to USD successfully!";
        }

        if(Objects.equals(currencyUnit,"pkr")){
            PKRCurrencyTypeStrategy pkrCurrencyTypeStrategy = new PKRCurrencyTypeStrategy();
            CurrencyContext.setCurrencyTypeStrategy(pkrCurrencyTypeStrategy);

            return "Currency unit set to PKR successfully!";
        }

        return "ERROR. Currency unit not available.";
    }

    public String addSubscriber(String type){
        Account account = Account.getAccountInstance();

        if(Objects.equals(type,"email")){
            EmailSubscriber emailSubscriber = new EmailSubscriber("abc@xyz.com");

            account.addSubscriber("email",emailSubscriber);

            return "Email subscription added successfull!";
        }
        if(Objects.equals(type,"sms")){
            SmsSubscriber smsSubscriber = new SmsSubscriber("03331114785");

            account.addSubscriber("sms",smsSubscriber);

            return "SMS subscription added successfull!";
        }

        return "Invalid subscription type";
    }

    public String addTransaction(Transaction transaction){
        Account account = Account.getAccountInstance();

        double currentBalance = account.getBalance();
        double newBalance = currentBalance - transaction.getAmount();

        account.addTransaction(transaction);
        account.setBalance(newBalance);

        return "Transaction successful!\n " +
                "Previous balance:" + currentBalance + "\n" +
                "New balance: "+account.getBalance();
    }
}
