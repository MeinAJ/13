package com.aj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ObjectMain1 {

    public static void main(String[] args) throws InterruptedException, IOException {
        File file = new File("D:\\1.sql");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        TrieTree dic = TrieTree.getInstance;
        String line;
        while ((line = reader.readLine()) != null) {
            if (line != null && line.length() >= 1) {
                dic.insert(line);
            }
        }

        System.out.println("dd");

        while (true) {
            Thread.sleep(10000);
        }
    }

}
