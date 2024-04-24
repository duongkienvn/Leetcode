package com.alltopic;

import java.lang.reflect.Array;
import java.util.*;

public class Test {
    public static void compare(ArrayList<String> words){
        int wordsLength = words.size();
        Map<String, Integer> map = new HashMap<>();
        for(String word: words){
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Collections.sort(words, (a, b) -> map.get(a).equals(map.get(b)) ? a.compareTo(b) : map.get(a) - map.get(b));
        for(String word: words){
            System.out.print(word + " ");
        }
    }
    public static void main(String[] args) {
        int[] n1 = {2, 3, 12, 4, 12, -2};
        int[] n2 = new int[n1.length];

        System.arraycopy(n1, 2, n2, 3, 2);
        System.out.println("n2 = " + Arrays.toString(n2));




    }
}
