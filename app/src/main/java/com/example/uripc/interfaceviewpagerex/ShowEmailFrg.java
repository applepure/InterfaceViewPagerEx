package com.example.uripc.interfaceviewpagerex;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Dor on 8/10/2016.
 */
public class ShowEmailFrg extends android.support.v4.app.Fragment {
TextView textView;
    MyListenerCons myListenerCons;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.emailfrg, container, false);
        // Inflate the layout for this fragment

        textView=(TextView)fragmentView.findViewById(R.id.mail);

        return fragmentView;

    }
    public void setMyText(){
        if (AppController.getInstance().getUser().getEmail()!=null) {
            textView.setText(AppController.getInstance().getUser().getEmail());
        }
    }

}
