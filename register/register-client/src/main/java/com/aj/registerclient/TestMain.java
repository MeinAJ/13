package com.aj.registerclient;

import com.aj.registerclient.test.SingletonClass;

import java.util.UUID;

public class TestMain {

    public static void main(String[] args) throws Exception {
//        RegisterClient registerClient = new RegisterClient(UUID.randomUUID().toString().replaceAll("-", ""));
//        registerClient.start();
        new Thread(){
            @Override
            public void run() {
                SingletonClass.();
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                SingletonClass.getInstance();
            }
        }.start();
    }

}
