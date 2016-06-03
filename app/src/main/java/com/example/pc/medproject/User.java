package com.example.pc.medproject;

/**
 * Created by PC on 17.05.2016.
 */
public class User {

    private String name;
    private String password;
    private double pesel;

    public User(String name, String password, double pesel){
        this.name = name;
        this.password = password;
        this.pesel = pesel;
    }
    public String getName(){
        return name;
    }

    public String getPassword(){
        return password;
    }
    public double getPesel(){
        return pesel;
    }
}
