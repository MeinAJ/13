package com.aj.registerclient.test;

public class SingletonClass {

    private static SingletonClass instance;

    public static SingletonClass getInstance() {
        if (instance == null) {
            synchronized (SingletonClass.class) {
                if (instance == null) {
                    instance = new SingletonClass();
                    System.out.println(instance);
                }
            }
        }
        return instance;
    }

}
