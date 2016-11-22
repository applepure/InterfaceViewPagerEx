package com.example.uripc.interfaceviewpagerex;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ShowUserFrg extends Fragment{
    TextView textView;
    MyListenerCons myListenerCons;
FireBaseLIstener fireBaseLIstener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.userfrg, container, false);
        // Inflate the layout for this fragment
        textView=(TextView)fragmentView.findViewById(R.id.user);


        return fragmentView;

    }


    public void setMyText(){
      if (AppController.getInstance().getUser().getUser()!=null) {
            textView.setText(AppController.getInstance().getUser().getUser());
        }
    }


}
