package com.aj.registerclient;

import java.util.UUID;

public class TestMain {

    public static void main(String[] args) {
        RegisterClient registerClient = new RegisterClient(UUID.randomUUID().toString().replaceAll("-", ""));
        registerClient.start();
    }

}
