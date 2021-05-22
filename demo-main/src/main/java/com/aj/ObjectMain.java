package com.aj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class ObjectMain {

    public static void main(String[] args) throws InterruptedException, IOException {
        File file = new File("D:\\2.sql");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        HashSet hashSet = new HashSet();
        String line;
        while ((line = reader.readLine()) != null) {
            hashSet.add(line);
        }

        System.out.println("11");
        while (true) {
            Thread.sleep(10000);
        }
    }

}
