package com.example.dpproject.ObserverPattern;

import java.util.HashMap;

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

    public void notify(String eventType, String data){
        for(String i: subscribers.keySet()){
            if(i == eventType){
                subscribers.get(i).update(data);
            }
        }
    }

    public void notifyAllSubscribers(String data){
        for(Subscriber subscriber: subscribers.values()){
            subscriber.update(data);
        }
    }
}
