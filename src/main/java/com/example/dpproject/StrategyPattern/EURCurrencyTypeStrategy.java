package com.example.dpproject.StrategyPattern;

public class EURCurrencyTypeStrategy implements CurrencyTypeStrategy {
    private String currencyUnit = "EUR";
    public double getTaxRate() {
        return 0.12;
    }

    public String getCurrencyUnit(){
        return this.currencyUnit;
    }
}
