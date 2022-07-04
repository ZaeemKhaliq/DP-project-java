package com.example.dpproject.StrategyPattern;

public class PKRCurrencyTypeStrategy implements CurrencyTypeStrategy {
    private String currencyUnit = "PKR";

    public double getTaxRate(){
        return 0.15;
    }

    public String getCurrencyUnit(){
        return this.currencyUnit;
    }
}
