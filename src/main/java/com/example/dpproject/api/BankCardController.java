package com.example.dpproject.api;

import com.example.dpproject.Entities.BankCard.*;
import com.example.dpproject.Service.BankCardService;
import com.example.dpproject.SingletonPattern.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("api/v1/bankcard")
@RestController
public class BankCardController {

    @Autowired
    BankCardService bankCardService;

    @PostMapping(path="createCreditCard/{type}")
    public String createCreditCard(@PathVariable("type") String type){
        Account account = Account.getAccountInstance();

        if(Objects.equals(type,"silver")){
            SilverCreditCard silverCreditCard = new SilverCreditCard(account);
            bankCardService.createCreditCard(silverCreditCard);

            return "Your silver credit card is created successfully!";
        }

        if(Objects.equals(type,"gold")){
            GoldCreditCard goldCreditCard = new GoldCreditCard(account);
            bankCardService.createCreditCard(goldCreditCard);

            return "Your gold credit card is created successfully!";
        }

        return "ERROR! Credit Card couldn't be created for unknown reasons.";
    }

    @PostMapping(path="createDebitCard/{type}")
    public String createDebitCard(@PathVariable("type") String type){
        Account account = Account.getAccountInstance();

        if(Objects.equals(type,"silver")){
            SilverDebitCard silverDebitCard = new SilverDebitCard(account);
            bankCardService.createDebitCard(silverDebitCard);

            return "Your silver debit card is created successfully!";
        }

        if(Objects.equals(type,"gold")){
            GoldDebitCard goldDebitCard = new GoldDebitCard(account);
            bankCardService.createDebitCard(goldDebitCard);

            return "Your gold debit card is created successfully!";
        }

        return "ERROR! Debit Card couldn't be created for unknown reasons.";
    }

    @GetMapping(path = "getCreditCard")
    public CreditCard getCreditCard(){
        return bankCardService.getCreditCard();
    }

    @GetMapping(path = "getDebitCard")
    public DebitCard getDebitCard(){
        return bankCardService.getDebitCard();
    }
}
