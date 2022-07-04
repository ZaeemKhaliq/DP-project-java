package com.example.dpproject.ObserverPattern;

public class EmailSubscriber implements Subscriber {
    private String email;

    public EmailSubscriber(String email){
        this.email = email;
    }

    public void update(String message){
        System.out.println("Sending notification to following email: " + this.email);
        System.out.println("...");
        System.out.println("Notification sent successfully!");
        System.out.println("Message sent:");
        System.out.println(message);
    }
}
