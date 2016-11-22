package com.example.uripc.interfaceviewpagerex;

/**
 * Created by Dor on 8/10/2016.
 */
public class MyListenerCons {
    FireBaseLIstener fireBaseLIstener;

    MyListenerCons(FireBaseLIstener fireBaseLIstener){
        this.fireBaseLIstener=fireBaseLIstener;
    }
    public void listenerMark(){
        fireBaseLIstener.onFirebaseDone();
    }
}
