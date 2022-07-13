package com.example.dpproject.StrategyPattern;

public class USDCurrencyTypeStrategy implements CurrencyTypeStrategy {
    private String currencyUnit = "USD";
    public double getTaxRate(){
        return 0.10d;
    }

    public String getCurrencyUnit(){
        return this.currencyUnit;
    }
}
