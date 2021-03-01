package com.aj.registerclient.test;

public class SingletonClass {

    private static SingletonClass instance;

    public SingletonClass getInstance() {
        if (instance == null) {
            synchronized (SingletonClass.class) {
                if (instance == null) {
                    SingletonClass.instance = new SingletonClass();
                    System.out.println(instance);
                }
            }
        }
        return SingletonClass.instance;
    }

}
