package com.aj;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class ObjectMain {

    public static void main(String[] args) throws InterruptedException, IOException {
        File file = new File("D:\\1.sql");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        HashSet hashSet = new HashSet();
        String line;
        while ((line = reader.readLine()) != null){
            hashSet.add(line);
        }

        while (true){
            Thread.sleep(10000);
        }
    }

}
