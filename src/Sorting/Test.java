package Sorting;

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
        ArrayList<String> words = new ArrayList<>();
        words.add("abcd");
        words.add("abe");
        words.add("abce");
        words.add("bbcd");
        words.add("abce");
        words.add("abg");
        compare(words);

        
    }
}
