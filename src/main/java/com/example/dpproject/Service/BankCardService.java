package com.example.dpproject.Service;

import com.example.dpproject.Entities.BankCard.CreditCard;
import com.example.dpproject.Entities.BankCard.DebitCard;
import org.springframework.stereotype.Service;

@Service
public class BankCardService {
    private CreditCard creditCard;
    private DebitCard debitCard;

    public void createCreditCard(CreditCard creditCard){
        this.creditCard = creditCard;
    }

    public void createDebitCard(DebitCard debitCard){
        this.debitCard = debitCard;
    }

    public CreditCard getCreditCard(){
        return this.creditCard;
    }

    public DebitCard getDebitCard(){
        return this.debitCard;
    }
}
