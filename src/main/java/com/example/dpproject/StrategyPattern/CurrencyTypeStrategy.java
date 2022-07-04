package com.example.dpproject.StrategyPattern;

public interface CurrencyTypeStrategy {
    public double getTaxRate();

    public String getCurrencyUnit();
}
