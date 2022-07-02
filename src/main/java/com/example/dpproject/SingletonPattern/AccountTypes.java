package com.example.dpproject.SingletonPattern;

public class AccountTypes {
    private static AccountTypes instance = null;
    private static String[] accountTypes;

    private AccountTypes(){
        AccountTypes.accountTypes = new String[]{"Young Saver", "Current"};
    }

    public static AccountTypes getInstance(){
        if(instance == null){
            instance = new AccountTypes();
        }

        return instance;
    }

    public String[] getAccountTypes(){
        return AccountTypes.accountTypes;
    }
}
