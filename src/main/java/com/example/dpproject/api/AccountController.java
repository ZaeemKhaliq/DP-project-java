package com.example.dpproject.api;

import com.example.dpproject.Entities.AccountForm.AccountForm;
import com.example.dpproject.Entities.BankCard.CreditCard;
import com.example.dpproject.Entities.BankCard.DebitCard;
import com.example.dpproject.Entities.Transaction.Transaction;
import com.example.dpproject.Service.AccountService;
import com.example.dpproject.SingletonPattern.Account;
import com.example.dpproject.StrategyPattern.CurrencyContext;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    public ResponseEntity<String> createAccount(@PathVariable("accountType") String accountType){
        AccountForm accountForm = getForm();

        String personName = accountForm.getName();
        String personFatherName = accountForm.getFatherName();
        String personAddress = accountForm.getAddress();
        String personCnicNum = accountForm.getCnicNum();
        int personAge = accountForm.getAge();

        if(personName == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Please provide your 'name' for account");
        }
        if(personFatherName == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Please provide your 'fatherName' for account");
        }
        if(personAddress == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Please provide your 'address' for account");

        }
        if(personCnicNum == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Please provide your 'cnicNum' for account");
        }
        if(!(personAge>=1)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Please provide your 'age' for account");
        }
        if((personAge>0 && personAge<18)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ERROR. Minimum age should be '18'");
        }

        return accountService.createAccount(accountType, accountForm);
    }

    @GetMapping(path = "getAccount")
    public Account getAccount(){
        return accountService.getAccount();
    }

    @PostMapping(path="addBalance")
    public ResponseEntity<String> addBalance(@RequestBody Account account){
        double balance = account.getBalance();

        Account account1 = Account.getAccountInstance();
        double accountBalance = account1.getBalance();
        accountBalance = accountBalance + balance;
        String accountType = account1.getAccountType();

        String currentCurrencyUnit = CurrencyContext.getCurrencyUnit();

        if(!Objects.equals(currentCurrencyUnit,"USD")){
            HashMap<String, Double> currencyConversionRates = CurrencyContext.getCurrencyConversionRates();
            String currencyUnitPair = currentCurrencyUnit+"_"+"USD";
            double currencyConversionRate = currencyConversionRates.get(currencyUnitPair);
            accountBalance = accountBalance * currencyConversionRate;
        }

        switch(accountType){
            case "Young Saver":
                if(accountBalance > 1000){
                    return ResponseEntity.status(403).body("Can't add balance.\nBalance limit for Young Saver's account is 1000 USD.");
                }
                break;
            case "Current":
                if(accountBalance > 10000){
                    return ResponseEntity.status(403).body("Can't add balance.\nBalance limit for Current account is 10000 USD.");
                }
                break;
        }

        return accountService.addBalance(balance);
    }

    @GetMapping(path = "getCurrencyContext")
    public HashMap<String, String> getCurrencyContext(){
        return accountService.getCurrencyContext();
    }

    @GetMapping(path = "getAllCurrencyContexts")
    public List<HashMap<String, String>> getAllCurrencyContexts(){
        return accountService.getAllCurrencyContexts();
    }

    @PostMapping(path = "changeCurrencyUnit/{currencyUnit}")
    public ResponseEntity<String> changeCurrencyUnit(@PathVariable("currencyUnit") String currencyUnit){
        if(!Account.checkInstance()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please create an account first!");
        }

        String currentCurrencyUnit = CurrencyContext.getCurrencyUnit();

        if(Objects.equals(currencyUnit, currentCurrencyUnit.toLowerCase())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Given currency unit is already set.");
        }

        return accountService.changeCurrencyUnit(currencyUnit);
    }

    @PostMapping(path = "addSubscriber/{type}")
    public String addSubscriber(@PathVariable("type") String type, @RequestParam String value){
        return accountService.addSubscriber(type, value);
    }

    @PostMapping(path = "removeSubscriber/{type}")
    public String removeSubscriber(@PathVariable("type") String type){
        return accountService.removeSubscriber(type);
    }

    @PostMapping(path = "makeTransaction")
    public ResponseEntity<List<String>> makeTransaction(@RequestBody Transaction transaction){
        Account account = Account.getAccountInstance();

        double accountBalance = account.getBalance();
        double transactionAmount = transaction.getAmount();

        List<String> response = new ArrayList<>();

        if(transactionAmount < 0){
            response.add("ERROR. Transaction amount is in negative.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if(accountBalance < transactionAmount){
            response.add("Your balance is insufficient for this transaction.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        return accountService.addTransaction(transaction);

    }

    @GetMapping(path = "getTransactions")
    public List<Transaction> getTransactions(){
        return accountService.getTransactions();
    }

    @GetMapping(path = "getSubscriptions")
    public HashMap<String, Object> getSubscriptions(){
        return accountService.getSubscriptions();
    }

    @PostMapping(path = "createCreditCard/{variant}")
    public ResponseEntity<String> createCreditCard(@PathVariable("variant") String variant){
        Account account = Account.getAccountInstance();

        if(account.getCreditCard() != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You already have a credit card.");
        }

        return accountService.createCreditCard(variant);
    }

    @PostMapping(path = "createDebitCard/{variant}")
    public ResponseEntity<String> createDebitCard(@PathVariable("variant") String variant){
        Account account = Account.getAccountInstance();

        if(account.getDebitCard() != null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You already have a debit card.");
        }

        return accountService.createDebitCard(variant);
    }

    @PutMapping(path = "changeCreditCard/{variant}")
    public ResponseEntity<String> changeCreditCard(@PathVariable("variant") String variant){
        Account account = Account.getAccountInstance();
        CreditCard currentCreditCard = account.getCreditCard();
        HashMap<String, String> cardDetails = currentCreditCard.getCardDetails();
        String cardVariant = cardDetails.get("cardVariant");

        if(Objects.equals(cardVariant.toLowerCase(),variant)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You already have that variant.\nPlease try a different one.");
        }

        return accountService.createCreditCard(variant);
    }

    @PutMapping(path = "changeDebitCard/{variant}")
    public ResponseEntity<String> changeDebitCard(@PathVariable("variant") String variant){
        Account account = Account.getAccountInstance();
        DebitCard currentDebitCard = account.getDebitCard();
        HashMap<String, String> cardDetails = currentDebitCard.getCardDetails();
        String cardVariant = cardDetails.get("cardVariant");

        if(Objects.equals(cardVariant,variant)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You already have that variant.\nPlease try a different one.");
        }

        return accountService.createDebitCard(variant);
    }

    @DeleteMapping(path="removeCreditCard")
    public ResponseEntity<String> removeCreditCard(){
        return accountService.removeCreditCard();
    }

    @DeleteMapping(path="removeDebitCard")
    public ResponseEntity<String> removeDebitCard(){
        return accountService.removeDebitCard();
    }
}
