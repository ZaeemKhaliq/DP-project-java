package com.example.dpproject.ObserverPattern;

public class SmsSubscriber implements Subscriber {
    private String smsNumber;

    public SmsSubscriber(String smsNumber){
        this.smsNumber = smsNumber;
    }

    public void update(String message){
        System.out.println("Sending notification on the following number: " + this.smsNumber);
        System.out.println("...");
        System.out.println("Notification sent successfully!");
        System.out.println("Message sent:");
        System.out.println(message);
    }
}
