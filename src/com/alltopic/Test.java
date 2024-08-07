package com.alltopic;

import java.util.*;

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
        Optional<String> optionalName = Optional.of("Alice");

        if (optionalName.isPresent()) {
            String name = optionalName.get();
            System.out.println("Name is: " + name);
        }

        String defaultName = (String) Optional.ofNullable(null).orElse("Default Name");
        System.out.println("Name is: " + defaultName);
    }
}
