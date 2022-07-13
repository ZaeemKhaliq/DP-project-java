package com.example.dpproject.ObserverPattern;

public class EmailSubscriber implements Subscriber {
    private String email;

    public EmailSubscriber(String email){
        this.email = email;
    }

    public String getField(){
        return this.email;
    }

    public String update(String message){
        String notification = "Transaction notification sent to '" + this.email + "' successfully!\n\n" +
                "Message sent: \"" + message + "\"";
        return notification;
    }
}
