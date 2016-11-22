package com.example.uripc.interfaceviewpagerex;

/**
 * Created by Dor on 8/10/2016.
 */
public class User {
    String user;
    String pass;
    String email;
    public User() {

    }
    public User(String email, String pass,String user) {
        this.user = user;
        this.pass = pass;
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public String getPass() {
        return pass;
    }

    public String getEmail() {
        return email;
    }
}
