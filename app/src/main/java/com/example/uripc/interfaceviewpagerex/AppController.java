package com.example.uripc.interfaceviewpagerex;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by uri pc on 10/08/2016.
 */
public class AppController extends Application {

    private static AppController mInstance= null;
     private User user;
    @Override
    public void onCreate() {
        super.onCreate();
    Firebase.setAndroidContext(getApplicationContext());
    mInstance = this;
    }
    public static  AppController getInstance() {
        if(mInstance == null){
            mInstance = new AppController();
        }
        return mInstance;
    }
    User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
