package com.GravityDailyHorscope;

import android.app.Application;

/**
 * Created by pratiksha goyal on 1/26/2016.
 */
public class GlobalClass extends Application {

    private String name;
    private String email;
    private String balance;


    public String getName() {

        return name;
    }

    public void setName(String aName) {

        name = aName;

    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String aEmail) {

        email = aEmail;
    }
    public String getBalance() {

        return balance;
    }

    public void setBalance(String aBalance) {

        email = aBalance;
    }

}