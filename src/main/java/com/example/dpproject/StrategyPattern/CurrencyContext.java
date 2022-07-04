package com.example.dpproject.StrategyPattern;

import com.example.dpproject.SingletonPattern.Account;

import java.util.HashMap;
import java.util.Map;

public class CurrencyContext {
    public static CurrencyTypeStrategy currencyTypeStrategy = new USDCurrencyTypeStrategy();
    public static HashMap<String, Double> currencyConversionRates;

    static{
        currencyConversionRates = new HashMap<String, Double>();
        currencyConversionRates.put("USD_PKR",204.5);
        currencyConversionRates.put("PKR_USD",0.0049);
        currencyConversionRates.put("EUR_PKR",213.2);
        currencyConversionRates.put("PKR_EUR",0.0047);
        currencyConversionRates.put("USD_EUR",0.96);
        currencyConversionRates.put("EUR_USD",1.04);
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
