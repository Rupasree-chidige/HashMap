package org.rupasree;
import org.rupasree.HashMapImpl.HashMap;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        HashMap<String,Integer> hashMap =new HashMap<>();
        hashMap.put("India", 20);
        hashMap.put("Srilanka", 30);
        hashMap.put("US", 50);
        ArrayList<String> keys = hashMap.keySet();
        for(int i=0; i<keys.size(); i++) {
            System.out.println(keys.get(i)+" "+hashMap.get(keys.get(i)));
        }

        hashMap.remove("India");
        System.out.println(hashMap.get("India"));
    }
}