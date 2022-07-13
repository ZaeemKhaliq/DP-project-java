package com.example.dpproject.ObserverPattern;

public class SmsSubscriber implements Subscriber {
    private String smsNumber;

    public SmsSubscriber(String smsNumber){
        this.smsNumber = smsNumber;
    }

    public String getField(){
        return this.smsNumber;
    }

    public String update(String message){
        String notification = "Transaction notification sent to '" + this.smsNumber + "' successfully!\n\n" +
                "Message sent: \"" + message + "\"";
        return notification;
    }
}
