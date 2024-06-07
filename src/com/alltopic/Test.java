package com.alltopic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void compare(ArrayList<String> words) {
        int wordsLength = words.size();
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Collections.sort(words, (a, b) -> map.get(a).equals(map.get(b)) ? a.compareTo(b) : map.get(a) - map.get(b));
        for (String word : words) {
            System.out.print(word + " ");
        }
    }

    public static void main(String[] args) {
        double n = 34.94949499494;
        System.out.println(String.format("%20.2f", n));
    }
}
