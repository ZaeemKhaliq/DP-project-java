package com.example.dpproject.Entities.AccountForm;

public class AccountForm {
    private String name;
    private String fatherName;
    private String address;
    private String cnicNum;
    private int age;

    public AccountForm(){}

    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }

    public void setFatherName(String fatherName){
        this.fatherName = fatherName;
    }
    public String getFatherName(){
        return this.fatherName;
    }

    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return this.address;
    }

    public void setCnicNum(String cnicNum){
        this.cnicNum = cnicNum;
    }
    public String getCnicNum(){
        return this.cnicNum;
    }

    public void setAge(int age){
        this.age = age;
    }
    public int getAge(){
        return this.age;
    }

}
