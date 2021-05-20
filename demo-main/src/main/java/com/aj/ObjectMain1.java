package com.aj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class ObjectMain1 {

    public static void main(String[] args) throws InterruptedException, IOException {
        File file = new File("D:\\1.sql");
        BufferedReader reader = new BufferedReader(new FileReader(file));
        TrieTree dic = TrieTree.getInstance;
        String line;
        while ((line = reader.readLine()) != null){
            dic.insert(line);
        }

        while (true){
            Thread.sleep(10000);
        }
    }

}
