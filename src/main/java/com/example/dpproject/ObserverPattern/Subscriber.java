package com.example.dpproject.ObserverPattern;

public interface Subscriber {
    public String getField();
    public String update(String message);
}
