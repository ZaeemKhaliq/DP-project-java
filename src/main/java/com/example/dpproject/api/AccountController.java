package com.example.dpproject.api;

import com.example.dpproject.Entities.AccountForm.AccountForm;
import com.example.dpproject.Entities.Transaction.Transaction;
import com.example.dpproject.Service.AccountService;
import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.StrategyPattern.CurrencyContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequestMapping("api/v1/account")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AccountController {

    @Autowired
    AccountService accountService;

    @PostMapping(path = "createAccountBuilder")
    public void createAccountBuilder(){
        accountService.createAccountBuilder();
    }

    @PostMapping(path = "setName")
    public String setName(@RequestBody AccountForm accountForm){

        String name = accountForm.getName();

        if(name == null){
            return "No name field provided in request body";
        }

        accountService.setName(name);
        return "Name added successfully!";
    }

    @PostMapping(path = "setFatherName")
    public String setFatherName(@RequestBody AccountForm accountForm){

        String fatherName = accountForm.getFatherName();

        if(fatherName == null){
            return "No fatherName field provided in request body";
        }

        accountService.setFatherName(fatherName);
        return "Father Name added successfully!";
    }

    @PostMapping(path = "setAddress")
    public String setAddress(@RequestBody AccountForm accountForm){

        String address = accountForm.getAddress();

        if(address == null){
            return "No address field provided in request body";
        }

        accountService.setAddress(address);
        return "Address added successfully!";
    }

    @PostMapping(path = "setCnicNum")
    public String setCnicNum(@RequestBody AccountForm accountForm){

        String cnicNum = accountForm.getCnicNum();

        if(cnicNum == null){
            return "No cnicNum field provided in request body";
        }

        accountService.setCnicNum(cnicNum);
        return "CNIC number added successfully!";
    }

    @PostMapping(path = "setAge")
    public String setAge(@RequestBody AccountForm accountForm){

        int age = accountForm.getAge();


        boolean ageExists = (age >= 1);

        if(!ageExists){
            return "No age field provided in request body";
        }


        accountService.setAge(age);
        return "Age added successfully!";
    }

    @GetMapping(path = "getAccountForm")
    public AccountForm getForm() {
        return accountService.getAccountForm();
    }

    @PostMapping(path = "createAccount/{accountType}")
    public String createAccount(@PathVariable("accountType") String accountType){
        AccountForm accountForm = getForm();

        String personName = accountForm.getName();
        String personFatherName = accountForm.getFatherName();
        String personAddress = accountForm.getAddress();
        String personCnicNum = accountForm.getCnicNum();
        int personAge = accountForm.getAge();

        if(personName == null){
            return "ERROR. Please provide your 'name' for account";
        }
        if(personFatherName == null){
            return "ERROR. Please provide your 'fatherName' for account";
        }
        if(personAddress == null){
            return "ERROR. Please provide your 'personAddress' for account";
        }
        if(personCnicNum == null){
            return "ERROR. Please provide your 'personCnicNum' for account";
        }
        if(!(personAge>=1)){
            return "ERROR. Please provide your 'age' for account";
        }
        if((personAge>0 && personAge<18)){
            return "ERROR. Minimum age should be '18'";
        }


        return accountService.createAccount(accountType, accountForm);
    }

    @GetMapping(path = "getAccount")
    public Account getAccount(){
        return accountService.getAccount();
    }

    @PostMapping(path = "changeCurrencyUnit/{currencyUnit}")
    public String changeCurrencyUnit(@PathVariable("currencyUnit") String currencyUnit){
        if(!Account.checkInstance()){
            return "Please create an account first!";
        }

        String currentCurrencyUnit = CurrencyContext.getCurrencyUnit();

        if(Objects.equals(currencyUnit, currentCurrencyUnit)){
            return "Given currency unit is already set.";
        }

        return accountService.changeCurrencyUnit(currencyUnit);
    }

    @PostMapping(path = "addSubscriber/{type}")
    public String addSubscriber(@PathVariable("type") String type){
        return accountService.addSubscriber(type);
    }

    @PostMapping(path = "makeTransaction")
    public String makeTransaction(@RequestBody Transaction transaction){
        Account account = Account.getAccountInstance();

        double accountBalance = account.getBalance();
        double transactionAmount = transaction.getAmount();

        if(transactionAmount < 0){
            return "ERROR. Transaction amount is in negative.";
        }

        if(accountBalance < transactionAmount){
            return "Your balance '"+ accountBalance +
                    " " + account.getCurrencyUnit() + "' " +
                    "is insufficient for transaction.";
        }

        return accountService.addTransaction(transaction);

    }
}
