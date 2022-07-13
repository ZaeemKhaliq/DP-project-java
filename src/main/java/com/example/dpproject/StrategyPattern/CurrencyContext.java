package com.example.dpproject.StrategyPattern;

import com.example.dpproject.SingletonPattern.Account;

import java.util.HashMap;

public class CurrencyContext {
    public static CurrencyTypeStrategy currencyTypeStrategy = new USDCurrencyTypeStrategy();
    public static HashMap<String, Double> currencyConversionRates;

    static{
        currencyConversionRates = new HashMap<String, Double>();
        currencyConversionRates.put("USD_PKR",210.87);
        currencyConversionRates.put("PKR_USD",0.0047);
        currencyConversionRates.put("EUR_PKR",211.37);
        currencyConversionRates.put("PKR_EUR",0.0047);
        currencyConversionRates.put("USD_EUR",1.00);
        currencyConversionRates.put("EUR_USD",1.00);
    }

    public static HashMap<String, Double> getCurrencyConversionRates(){
        return CurrencyContext.currencyConversionRates;
    }

    public static void setCurrencyTypeStrategy(CurrencyTypeStrategy currencyTypeStrategy){
        CurrencyContext.currencyTypeStrategy = currencyTypeStrategy;
        Account.getAccountInstance().changeBalanceCurrencyType();
    }

    public static double getTaxRate(){
        return CurrencyContext.currencyTypeStrategy.getTaxRate();
    }

    public static String getCurrencyUnit(){
        return CurrencyContext.currencyTypeStrategy.getCurrencyUnit();
    }
}
