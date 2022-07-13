package com.example.dpproject.ObserverPattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Publisher {
    private HashMap<String, Subscriber> subscribers;

    public Publisher(){
        this.subscribers = new HashMap<String, Subscriber>();
    }

    public void subscribe(String eventType, Subscriber subscriber){
        subscribers.put(eventType, subscriber);
    }

    public void unsubscribe(String eventType, Subscriber subscriber){
        subscribers.remove(eventType, subscriber);
    }

    public Subscriber getSubscriber(String eventType){
        Subscriber subscriber = this.subscribers.get(eventType);
        return subscriber;
    }

    public HashMap<String, Subscriber> getSubscribers(){
        return this.subscribers;
    }

    public String notify(String eventType, String data){
        String message = "Subscription doesn't exists";
        for(String i: subscribers.keySet()){
            if(i == eventType){
                message = subscribers.get(i).update(data);
            }
        }

        return message;
    }

    public List<String> notifyAllSubscribers(String data){
        List<String> messages = new ArrayList<>();

        for(Subscriber subscriber: subscribers.values()){
            String message = subscriber.update(data);
            messages.add(message);
        }

        return messages;
    }
}
