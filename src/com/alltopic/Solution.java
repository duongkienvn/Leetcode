package com.alltopic;

import java.util.*;

public class Solution {
    public static boolean isSubsequence(String word, String s) {
        int i = 0, j = 0;
        while (i < word.length() && j < s.length()) {
            if (word.charAt(i) == s.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == word.length();
    }

    public static String findLongestWord(String s, List<String> dictionary) {
        //       Collections.sort(dictionary, (a, b) -> a.length() != b.length() ? b.length() - a.length() : a.compareTo(b));
        Collections.sort(dictionary, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.length() != o2.length()) {
                    return o2.length() - o1.length();
                }
                return o1.compareTo(o2);
            }
        });
        for (String word : dictionary) {
            if (isSubsequence(word, s)) return word;
        }
        return "";
    }

    public int maximumGap(int[] nums) {
        Arrays.sort(nums);
        int numsLength = nums.length;
        if (numsLength < 2) return 0;
        int maximum = Integer.MIN_VALUE;
        int i = 0, j = 1;
        int distance = 0;
        while (i < numsLength && j < numsLength) {
            distance = nums[j] - nums[i];
            if (maximum < distance) {
                maximum = distance;
            }
            i++;
            j++;
        }
        return maximum;
    }

    public int findKthLargest(int[] nums, int k) {

        return nums[k - 1];
    }

    public static int hIndex(int[] citations) {
        Arrays.sort(citations);
        return 1;
    }

    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        Arrays.sort(nums);

        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxSubsetSize = 1;
        int maxSubsetIndex = 0;

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] > maxSubsetSize) {
                        maxSubsetSize = dp[i];
                        maxSubsetIndex = i;
                    }
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        int currSize = maxSubsetSize;
        int currNum = nums[maxSubsetIndex];
        for (int i = maxSubsetIndex; i >= 0; i--) {
            if (currSize == dp[i] && currNum % nums[i] == 0) {
                result.add(nums[i]);
                currNum = nums[i];
                currSize--;
            }
        }

        return result;
    }

    public int[] findRightInterval(int[][] intervals) {
        int row = intervals.length;
        int col = intervals[0].length;
        int[] result = new int[row];
        if (row == 1) {
            return new int[-1];
        }
        int idx = 0;
        result[idx++] = -1;
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                if (intervals[i][j] <= intervals[i - 1][j - 1]) {

                }
            }
        }

        return result;
    }

    public static String longestWord(String[] words) {
        Arrays.sort(words);
        Set<String> builtWord = new HashSet<>();
        String result = "";
        for (String word : words) {
            if (word.length() == 1 || builtWord.contains(word.substring(0, word.length() - 1))) {
                builtWord.add(word);
                if (word.length() > result.length()) {
                    result = word;
                }
            }

        }
        return result;
    }

    public static int minMoves2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i : nums) {
            set.add(i);
        }
        if (set.size() == 1) return 0;
        Arrays.sort(nums);
        int numsLength = nums.length;
        int cnt = 0;
        int max = nums[numsLength - 1];
        int half = max / 2;
        System.out.println(half);
        if (max % 2 == 0) {
            for (int i : nums) {
                if (half > i) cnt += half - i;
                else if (half < i) cnt += i - half;
            }

        } else {
            half++;
            for (int i : nums) {
                if (half > i) cnt += half - i;
                else if (half < i) cnt += i - half;
            }
        }
        return cnt;
    }

    public static String maximumOddBinaryNumber(String s) {
        TreeMap<Character, Integer> map = new TreeMap<>();

        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
        String resultOne = "";
        String resultZero = "";
        char lastOne = ' ';
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getKey() == '0') {
                for (int i = 0; i < entry.getValue(); i++) {
                    resultZero += entry.getKey();
                }
            } else if (entry.getKey() == '1' && entry.getValue() > 1) {
                for (int i = 1; i <= entry.getValue() - 1; i++) {
                    resultOne += entry.getKey();
                }
                lastOne = entry.getKey();
            } else {
                resultZero += '1';
                return resultZero;
            }
        }
        System.out.println(resultOne);
        System.out.println(resultZero);
        return resultOne + resultZero + lastOne;
    }

    public static String maximumOddBinaryNumberLeetcode(String s) {
        int countOne = 0, countZero = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') countOne++;
            else countZero++;
        }
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < countOne - 1; i++) {
            result.append('1');
        }
        for (int i = 0; i < countZero; i++) {
            result.append('0');
        }
        result.append('1');
        return result.toString();
    }

    public static void moveZeroes(int[] nums) {
//      Move all zeros to the end of the array, using the two pointers technique.
        int numsLength = nums.length;
        int pointerOne = 0;
        int pointerTwo = 0;
        while (pointerTwo < numsLength) {
            if (nums[pointerTwo] != 0) {
                int temp = nums[pointerOne];
                nums[pointerOne] = nums[pointerTwo];
                nums[pointerTwo] = temp;
                pointerOne++;
            }
            pointerTwo++;
        }
    }

    public static void swapCharacter(char[] charArray, int left, int right) {
        char temp = charArray[left];
        charArray[left] = charArray[right];
        charArray[right] = temp;
    }

    public static String reverseVowels(String s) {
        // reverse vowels of string
        char[] chars = s.toCharArray();
        HashSet<Character> vowels = new HashSet<>();
        vowels.add('u');
        vowels.add('e');
        vowels.add('o');
        vowels.add('a');
        vowels.add('i');
        vowels.add('U');
        vowels.add('E');
        vowels.add('O');
        vowels.add('A');
        vowels.add('I');
        int left = 0, right = s.length() - 1;
        while (left < right) {
            while (left < right && !vowels.contains(s.charAt(left))) {
                left++;
            }
            while (left < right && !vowels.contains(s.charAt(right))) {
                right--;
            }
            if (left < right) {
                swapCharacter(chars, left, right);
                left++;
                right--;
            }
        }
        return String.valueOf(chars);
    }

    public static int[] sortedSquares(int[] nums) {
        int numsLength = nums.length;
        int[] result = new int[numsLength];

        for (int i = 0; i < numsLength; i++) {
            result[i] = (int) Math.pow(nums[i], 2);
        }

        Arrays.sort(result);

        return result;
    }

    public static boolean sortByAbs(int a, int b) {
        return Math.abs(a) < Math.abs(b);
    }

    public static int[] sortedSquaresLeetcode(int[] nums) {
        int numsLength = nums.length;
        int[] result = new int[numsLength];

        // find first postition of non-negative factor
        int nonNegativeStart = 0;
        while (nonNegativeStart < numsLength && nums[nonNegativeStart] < 0) {
            nonNegativeStart++;
        }

        int left = nonNegativeStart - 1; // pointer move from maximum negative factor to minimum negative factor
        int right = nonNegativeStart; // pointer move from minimum nonnegative factor to maximum nonnegative factor
        int index = 0;

        while (left >= 0 && right < numsLength) {
            if (nums[left] * nums[left] < nums[right] * nums[right]) {
                result[index++] = nums[left] * nums[left];
                left--;
            } else {
                result[index++] = nums[right] * nums[right];
                right++;
            }
        }

        while (left >= 0) {
            result[index++] = nums[left] * nums[left];
            left--;
        }

        while (right < numsLength) {
            result[index++] = nums[right] * nums[right];
            right++;
        }

        return result;
    }

    public static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }

    public static boolean validPalindrome(String s) {
        int length = s.length();

//        abcdcgba
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return isPalindrome(s, left + 1, right) || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public static String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;

        int left = 0, right = length - 1;
        while (left < right) {
            while (left < right && !Character.isLetter(s.charAt(left))) {
                left++;
            }

            while (left < right && !Character.isLetter(s.charAt(right))) {
                right--;
            }

            if (left < right) {
                swapCharacter(chars, left, right);
                left++;
                right--;
            }
        }
        return String.valueOf(chars);
    }

    public static boolean isLongPressedName(String name, String typed) {
        int nameLength = name.length();
        int typedLength = typed.length();

        int pointerName = 0, pointerTyped = 0;
        while (pointerTyped < typedLength) {

            if (pointerName < nameLength && name.charAt(pointerName) == typed.charAt(pointerTyped)) {
                pointerName++;
                pointerTyped++;
            } else if (pointerTyped > 0 && typed.charAt(pointerTyped) == typed.charAt(pointerTyped - 1)) pointerTyped++;
            else return false;
        }

        return pointerName == nameLength;
    }

    public static void duplicateZeros(int[] arr) {
        int n = arr.length;
        int zeros = 0;

        for (int num : arr) {
            if (num == 0) zeros++;
        }

        int orginalIndex = n - 1;
        int modifiedIndex = n - 1 + zeros;

        while (orginalIndex >= 0 && modifiedIndex >= 0) {

            if (arr[orginalIndex] == 0) {
                if (modifiedIndex < n) {
                    arr[modifiedIndex] = 0;
                }
                modifiedIndex--;
            }

            if (orginalIndex < n && modifiedIndex < n) {
                arr[modifiedIndex] = arr[orginalIndex];
            }
            modifiedIndex--;
            orginalIndex--;
        }
    }

    public static int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int maxScore = 0;
        int tokensLength = tokens.length;
        if (power < tokens[0]) return 0;

        for (int i = 0; i < tokensLength; i++) {
            if (power > tokens[i]) {
                power -= tokens[i];
                maxScore++;
            }
            if (maxScore == 1) {

            }
        }
        return maxScore;
    }

    public static int rightToLeft(int n) {
        if (n == 1) return 1;
        if (n % 2 == 1) return 2 * leftToRight(n / 2);
        return 2 * leftToRight(n / 2) - 1;
    }

    public static int leftToRight(int n) {
        if (n == 1) return 1;
        return 2 * rightToLeft(n / 2);
    }

    public static int lastRemaining(int n) {
        return leftToRight(n);
    }

    public static boolean isSymmetrical(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    public static int minimumLength(String s) {

        int left = 0, right = s.length() - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            char currentChar = s.charAt(left);
            while (left <= right && s.charAt(left) == currentChar) {
                left++;
            }
            while (left <= right && s.charAt(right) == currentChar) {
                right--;
            }
        }
        return right - left + 1;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> result = new ArrayList<>();
        int arrLength = arr.length;
        for (int i = 0; i < arrLength; i++) {

        }
        return result;
    }

    public static String customSortString(String order, String s) {
        String result = "";
        String tmp = "";
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        List<Character> list = new ArrayList<>();

        for (char c : order.toCharArray()) {
            set.add(c);
        }

        int cnt = 0;
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (set.contains(currentChar)) {
                list.add(currentChar);
            } else tmp += currentChar;
        }
        for (char c : list) {
            System.out.println(c);
        }
        Collections.sort(list, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                int index1 = order.indexOf(o1);
                int index2 = order.indexOf(o2);
                return Integer.compare(index1, index2);
            }
        });

        for (char c : list) {
            result += c;
        }
        return result + tmp;
    }

    public static int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i : nums) {
            frequency.put(i, frequency.getOrDefault(i, 0) + 1);
        }

        int maxFrequency = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (maxFrequency < entry.getValue()) {
                maxFrequency = entry.getValue();
            }
        }

        int result = 0;
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == maxFrequency) {
                result += maxFrequency;
            }
        }
        return result;
    }

    public static String customSortStringGPT(String order, String s) {
        // Create a map to store the index of each character in the order string
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            map.put(order.charAt(i), i);
        }

        // Sort the characters of string s based on the custom order
        Character[] charArray = new Character[s.length()];
        for (int i = 0; i < s.length(); i++) {
            charArray[i] = s.charAt(i);
        }
        Arrays.sort(charArray, new Comparator<Character>() {
            public int compare(Character c1, Character c2) {
                return map.getOrDefault(c1, Integer.MAX_VALUE) - map.getOrDefault(c2, Integer.MAX_VALUE);
            }
        });

        // Build the sorted string
        StringBuilder sb = new StringBuilder();
        for (char c : charArray) {
            sb.append(c);
        }

        return sb.toString();
    }

    public static int getCommon(int[] nums1, int[] nums2) {
        int nums1Length = nums1.length;
        int nums2Length = nums2.length;

        int i = 0, j = 0;
        while (i < nums1Length && j < nums2Length) {
            if (nums1[i] == nums2[j]) {
                return nums1[i];
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                j++;
            }
        }
        return -1;
    }

    public static int findLUSlength(String a, String b) {
        if (a.equals(b)) return -1;
        return Math.max(a.length(), b.length());
    }

    public static boolean isSubsequence2(String a, String b) {
        int i = 0, j = 0;
        while (i < a.length() && j < b.length()) {
            if (a.charAt(i) == b.charAt(j)) {
                i++;
            }
            j++;
        }
        return i == a.length();
    }

    public static int findLUSlength(String[] strs) {
        int strsLength = strs.length;
        int maxLength = -1;

        for (int i = 0; i < strsLength; i++) {
            boolean isUncommon = true;
            for (int j = 0; j < strsLength; j++) {
                if (i != j && isSubsequence2(strs[i], strs[j])) {
                    isUncommon = false;
                    break;
                }
            }
            if (isUncommon) {
                maxLength = Math.max(maxLength, strs[i].length());
            }
        }
        return maxLength;
    }

    public static int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int maxRadius = 0;
        int heaterIndex = 0;
        for (int house : houses) {
            while (heaterIndex < heaters.length - 1 && Math.abs(heaters[heaterIndex + 1] - house) <= Math.abs(heaters[heaterIndex] - house)) {
                heaterIndex++;
            }
            maxRadius = Math.max(maxRadius, Math.abs(heaters[heaterIndex] - house));
        }
        return maxRadius;

    }

    public static int startPosition(int[] a, int target) {
        int length = a.length;
        int left = 0, right = length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == target) {
                result = mid;
                right = mid - 1;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static int lastPosition(int[] a, int target) {
        int length = a.length;
        int left = 0, right = length - 1;
        int result = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (a[mid] == target) {
                result = mid;
                left = mid + 1;
            } else if (a[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }

    public static int[] searchRange(int[] a, int target) {
        int[] result = new int[2];
        result[0] = startPosition(a, target);
        result[1] = lastPosition(a, target);
        return result;
    }

    public static List<String> topKFrequent(String[] words, int k) {
        int wordsLength = words.length;
        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words) {
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);
        }
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int frequencyCompare = frequency.get(o2).compareTo(frequency.get(o1));
                if (frequencyCompare == 0) {
                    return o1.compareTo(o2);
                }
                return frequencyCompare;
            }
        });
        List<String> temp = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            boolean check = false;
            for (int j = 0; j < i; j++) {
                if (words[i].equals(words[j])) {
                    check = true;
                    break;
                }
            }
            if (!check) {
                temp.add(words[i]);
            }
        }
        List<String> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(temp.get(i));
        }
        return result;
    }

    public static List<String> topKFrequentGPT(String[] words, int k) {
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.put(word, frequencyMap.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> priorityQueue = new PriorityQueue<>((a, b) -> frequencyMap.get(a).equals(frequencyMap.get(b)) ? b.compareTo(a) : frequencyMap.get(a) - frequencyMap.get(b));

        for (String word : frequencyMap.keySet()) {
            priorityQueue.offer(word);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        List<String> result = new ArrayList<>();
        while (!priorityQueue.isEmpty()) {
            result.add(priorityQueue.poll());
        }
        Collections.reverse(result);
        return result;
    }

    public int[] topKFrequent(int[] nums, int k) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        int[] tmp = new int[k];
        int idx = 0;
        int n = nums.length;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (map.containsKey(nums[i])) {
                int cnt = map.get(nums[i]);
                cnt++;
                map.put(nums[i], cnt);
            } else {
                map.put(nums[i], 1);
            }
        }
        List<Map.Entry<Integer, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> entry1, Map.Entry<Integer, Integer> entry2) {
                return entry2.getValue().compareTo(entry1.getValue());
            }
        });
        for (Map.Entry<Integer, Integer> entry : entryList) {
            arr.add(entry.getKey());
        }
        for (int i = 0; i < k; i++) {
            tmp[idx++] = arr.get(i);
        }
        return tmp;

    }

    public static String largestWordCount(String[] messages, String[] senders) {
        String result = "";
        int messagesLength = messages.length;
        int sendersLength = senders.length;
        Map<String, Integer> countWords = new HashMap<>();
        for (int i = 0; i < messagesLength; i++) {
            String[] word = messages[i].split(" ");
            countWords.put(senders[i], countWords.getOrDefault(senders[i], 0) + word.length);
        }

        List<Map.Entry<String, Integer>> list = new ArrayList<>(countWords.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> entry1, Map.Entry<String, Integer> entry2) {
                if (entry1.getValue() == entry2.getValue()) {
                    return entry1.getKey().compareTo(entry2.getKey());
                }
                return entry1.getValue() - entry2.getValue();
            }
        });

        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        int maxWordCount = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : list) {
            if (maxWordCount <= entry.getValue()) {
                maxWordCount = entry.getValue();
                result = entry.getKey();
            }
        }

        return result;
    }

    public static int numSubarraysWithSum(int[] nums, int goal) {
        int numsLength = nums.length;
        int countSubArrays = 0;
        int left = 0;
        int sum = 0;
        for (int right = 0; right < numsLength; right++) {
            sum += nums[right];
            if (sum == goal) {
                countSubArrays++;
                continue;
            }
            while (left < numsLength && sum != goal) {
                sum -= nums[left];
                if (sum == goal) {
                    countSubArrays++;
                    left++;
                }

            }
        }
        return countSubArrays;
    }

    public static int numSubarraysWithSumC2(int[] nums, int goal) {
        int numsLength = nums.length;
        int[] prefix = new int[numsLength];
        prefix[0] = nums[0];
        for (int i = 1; i < numsLength; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        int cnt = 0;
        int length = prefix.length;
        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (prefix[j] - prefix[i] == goal) {
                    cnt += goal;
                }
            }
        }
        return cnt;

    }

    public static String largestWordCountGPT(String[] messages, String[] senders) {
        String largestSender = "";
        int messagesLength = messages.length;
        Map<String, Integer> countWords = new HashMap<>();
        for (int i = 0; i < messagesLength; i++) {
            String[] word = messages[i].split(" ");
            countWords.put(senders[i], countWords.getOrDefault(senders[i], 0) + word.length);
        }

        int maxWordCount = Integer.MIN_VALUE;
        for (Map.Entry<String, Integer> entry : countWords.entrySet()) {
            String sender = entry.getKey();
            int wordCount = entry.getValue();
            if (maxWordCount < wordCount || (maxWordCount == wordCount && sender.compareTo(largestSender) > 0)) {
                maxWordCount = wordCount;
                largestSender = sender;
            }
        }

        return largestSender;
    }

    public static int findPairs(int[] nums, int k) {

        int numsLength = nums.length;
        Set<Integer> set = new HashSet<>();
        int result = 0;
        Arrays.sort(nums);
        int left = 0;
        while (left < numsLength) {
            int right = left + 1;
            while (right < numsLength && Math.abs(nums[right] - nums[left]) != k) {
                right++;
            }
            if (right < numsLength && Math.abs(nums[left] - nums[right]) == k) {
                set.add(nums[left]);
            }
            left++;
        }
        return set.size();
    }

    public static int findPairsGPT(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            countMap.put(num, countMap.getOrDefault(num, 0) + 1);
        }

        int count = 0;
        for (int key : countMap.keySet()) {
            if (k == 0 && countMap.get(key) > 1) {
                count++;
            } else if (k > 0 && countMap.containsKey(key + k)) {
                count++;
            }
        }
        return count;
    }

    public static int findMinDifference(List<String> timePoints) {

        List<Integer> minutes = new ArrayList<>();
        for (int i = 0; i < timePoints.size(); i++) {
            String[] time = timePoints.get(i).split(":");
            int hour = Integer.valueOf(time[0]);
            int minute = Integer.valueOf(time[1]);
            int totalMinute = hour * 60 + minute;
            minutes.add(totalMinute);
        }

        Collections.sort(minutes);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.size(); i++) {
            int diff = minutes.get(i) - minutes.get(i - 1);
            minDiff = Math.min(minDiff, diff);
        }
        System.out.println(minDiff);
        int lastDiff = minutes.get(minutes.size() - 1) - minutes.get(0);
        minDiff = Math.min(minDiff, 24 * 60 - lastDiff);

        return minDiff;
    }

    public static int findLongestChain(int[][] pairs) {

        int rowLength = pairs.length;
        int colLength = pairs[0].length;
        for (int i = 0; i < rowLength; i++) {
            for (int j = 0; j < colLength - 1; j++) {
                for (int k = j + 1; k < colLength; k++) {
                    if (pairs[i][j] > pairs[i][k]) {
                        int temp = pairs[i][j];
                        pairs[i][j] = pairs[i][k];
                        pairs[i][k] = temp;
                    }
                }
            }
        }
        int longestChain = 0;
        for (int i = 0; i < rowLength - 1; i++) {
            System.out.println(pairs[i][colLength - 1] + " " + pairs[i + 1][0]);
            if (pairs[i][colLength - 1] < pairs[i + 1][0]) {
                longestChain++;
            }
        }
        return longestChain;
    }

    public static int pivotInteger(int n) {
        int result = -1;
        int[] prefix = new int[n];
        prefix[0] = 1;
        prefix[1] = 3;
        for (int i = 2; i < n; i++) {
            prefix[i] = prefix[i - 1] + i + 1;
        }
        for (int i = 1; i < prefix.length; i++) {
            if (prefix[prefix.length - 1] - prefix[i - 1] == prefix[i]) {
                result = i + 1;
            }
        }
        for (int i : prefix) {
            System.out.print(i + " ");
        }
        System.out.println();
        return result;

    }

    public static boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i : arr) {
            frequency.put(i, frequency.getOrDefault(i, 0) + 1);
        }

        Set<Integer> check = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (check.contains(entry.getValue())) {
                return false;
            }
            check.add(entry.getValue());
        }

        return true;
    }

    public static int[] productExceptSelf(int[] nums) {
        int numsLength = nums.length;
        int[] result = new int[numsLength];
        int[] prefixProducts = new int[numsLength];
        int[] suffixProduct = new int[numsLength];

        prefixProducts[0] = 1;
        for (int i = 1; i < numsLength; i++) {
            prefixProducts[i] = prefixProducts[i - 1] * nums[i - 1];
        }

        suffixProduct[numsLength - 1] = 1;
        for (int i = numsLength - 2; i >= 0; i--) {
            suffixProduct[i] = suffixProduct[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < numsLength; i++) {
            result[i] = prefixProducts[i] * suffixProduct[i];
        }

        return result;
    }

    public static int[] productExceptSelfGPT(int[] nums) {
        int n = nums.length;

        // Initialize arrays to store prefix and suffix products
        int[] prefixProducts = new int[n];
        int[] suffixProducts = new int[n];

        // Fill prefixProducts array
        prefixProducts[0] = 1;
        for (int i = 1; i < n; i++) {
            prefixProducts[i] = prefixProducts[i - 1] * nums[i - 1];
        }

        // Fill suffixProducts array
        suffixProducts[n - 1] = 1;
        for (int i = n - 2; i >= 0; i--) {
            suffixProducts[i] = suffixProducts[i + 1] * nums[i + 1];
        }

        // Calculate the product except self
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = prefixProducts[i] * suffixProducts[i];
        }

        for (int i : prefixProducts) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i : suffixProducts) {
            System.out.print(i + " ");
        }
        System.out.println();

        return result;
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int numsLength = nums.length;
        int[] result = new int[numsLength];

        int[] sortedNums = Arrays.copyOf(nums, numsLength);
        Arrays.sort(sortedNums);

        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < numsLength; i++) {
            if (!numCount.containsKey(sortedNums[i])) {
                numCount.put(sortedNums[i], i);
            }
        }

        for (int i = 0; i < numsLength; i++) {
            result[i] = numCount.get(nums[i]);
        }

        return result;
    }

    public static int[] smallerNumbersThanCurrentGPT(int[] nums) {
        int numsLength = nums.length;
        int[] result = new int[numsLength];

        int[] sortedNums = Arrays.copyOf(nums, numsLength);
        Arrays.sort(sortedNums);

        Map<Integer, Integer> numCount = new HashMap<>();
        for (int i = 0; i < numsLength; i++) {
            numCount.putIfAbsent(sortedNums[i], i);
        }

        for (int i = 0; i < numsLength; i++) {
            result[i] = numCount.get(nums[i]);
        }

        return result;
    }

    public static int findMaxLength(int[] nums) {
        int numsLength = nums.length;
        int maxLen = 0, cnt = 0;

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        for (int i = 0; i < numsLength; i++) {
            if (nums[i] == 0) {
                cnt--;
            } else {
                cnt++;
            }

            if (map.containsKey(cnt)) {
                maxLen = Math.max(maxLen, i - map.get(cnt));
            } else {
                map.put(cnt, i);
            }
        }
        return maxLen;
    }

    public static int sumOfDigits(int n) {
        int sum = 0;
        while (n != 0) {
            int r = n % 10;
            sum += r;
            n /= 10;
        }

        return sum;
    }

    public static int countLargestGroup(int n) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        Map<Integer, Integer> sumOfDigits = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            int sum = sumOfDigits(i);
            sumOfDigits.put(i, sum);
        }

        for (int i = 1; i <= n; i++) {
            int sum = sumOfDigits(i);
            if (sumOfDigits.get(i) == sum && !map.containsKey(sum)) {
                map.put(sum, new ArrayList<>());
            }


        }

        return 1;

    }

    public static String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : secret.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        // Kiểm tra từng chữ số trong đoán của người chơi
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s == g) {
                bulls++; // Nếu chữ số đúng vị trí, tăng số lượng bulls
                map.put(s, map.get(s) - 1); // Giảm số lượng chữ số còn lại trong số bí mật
            }
        }

        // Kiểm tra cows
        for (int i = 0; i < secret.length(); i++) {
            char s = secret.charAt(i);
            char g = guess.charAt(i);
            if (s != g && map.containsKey(g) && map.get(g) > 0) {
                cows++; // Nếu chữ số không đúng vị trí nhưng vẫn có trong số bí mật, tăng số lượng cows
                map.put(g, map.get(g) - 1); // Giảm số lượng chữ số còn lại trong số bí mật
            }
        }

        return bulls + "A" + cows + "B";
    }

    public static int[] arrayRankTransform(int[] arr) {
        int arrLength = arr.length;
        int[] result = new int[arrLength];

        TreeSet<Integer> sorted = new TreeSet<>();
        for (int i : arr) {
            sorted.add(i);
        }

        Map<Integer, Integer> sequence = new HashMap<>();
        int idx = 1;
        for (int set : sorted) {
            sequence.put(set, idx++);
        }

        for (int i = 0; i < arrLength; i++) {
            result[i] = sequence.get(arr[i]);
        }

        return result;
    }

    public static int[] arrayRankTransformC2(int[] arr) {
        int arrLength = arr.length;
        int[] copy = Arrays.copyOf(arr, arrLength);

        int index = 1;
        Arrays.sort(copy);
        Map<Integer, Integer> rankMap = new HashMap<>();
        for (int i : copy) {
            if (!rankMap.containsKey(i)) {
                rankMap.put(i, index++);
            }
        }

        int[] result = new int[arrLength];
        for (int i = 0; i < arrLength; i++) {
            result[i] = rankMap.get(arr[i]);
        }

        return result;
    }

    public static String reorganizeString(String s) {
        int length = s.length();
        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : s.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }


        return "";
    }

    public static int findMaxK(int[] nums) {
        int numsLength = nums.length;
        int result = -1;
        Arrays.sort(nums);
        Set<Integer> set = new LinkedHashSet<>();

        for (int i : nums) {
            if (set.contains(-i)) {
                result = i;
            }
            set.add(i);
        }

        return result;
    }

    public static String[] sortPeople(String[] names, int[] heights) {
        TreeMap<Integer, String> map = new TreeMap<>(Collections.reverseOrder());

        for (int i = 0; i < names.length; i++) {
            map.put(heights[i], names[i]);
        }

        String[] sortedNames = new String[names.length];
        int index = 0;

        for (String name : map.values()) {
            sortedNames[index++] = name;
        }

        return sortedNames;
    }

    public static int unequalTriplets(int[] nums) {
        int numsLength = nums.length;
        int result = 0;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numsLength; i++) {
            map.put(i, nums[i]);
        }

        for (int i = 0; i < numsLength; i++) {

        }
        return result;
    }

    public static int findDuplicate(int[] nums) {
        int numsLength = nums.length;

        Map<Integer, Integer> map = new HashMap<>();
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= 2) {
                return entry.getKey();
            }
        }

        return 0;
    }

    public static int findDuplicateC2(int[] nums) {
        boolean taken[] = new boolean[nums.length];
        for (int i : nums) {
            if (taken[i]) {
                return i;
            }
            taken[i] = true;
        }
        return 0;
    }

    public static int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {

        return 1;
    }

    public static boolean isGood(int[] nums) {
        int numsLength = nums.length;

        Arrays.sort(nums);
        if (numsLength != nums[numsLength - 1] + 1
                || nums[numsLength - 1] != nums[numsLength - 2]) {
            return false;
        }

        for (int i = 1; i < numsLength; i++) {
            if (i != nums[i - 1]) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnagram(String word, String s) {
        char charWords[] = word.toCharArray();
        char charS[] = s.toCharArray();

        Arrays.sort(charS);
        Arrays.sort(charWords);

        return Arrays.equals(charS, charWords);
    }

    public static List<String> removeAnagrams(String[] words) {
        List<String> result = new ArrayList<>();
        int wordsLength = words.length;
        result.add(words[0]);

        for (int i = 1; i < wordsLength; i++) {
            if (!isAnagram(words[i - 1], words[i])) {
                result.add(words[i]);
            }
        }

        return result;
    }

    public static List<String> removeAnagrams(List<String> words) {
        String previous = "";
        List<String> result = new ArrayList<>();
        for (String word : words) {
            char charArrays[] = word.toCharArray();
            Arrays.sort(charArrays);
            String current = String.valueOf(charArrays);
            if (!current.equals(previous)) {
                result.add(word);
                previous = current;
            }
        }

        return result;
    }

    public static int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // Đánh dấu tất cả các số âm và các số lớn hơn n bằng 1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }

        // Đánh dấu các số có mặt trong mảng
        for (int i = 0; i < n; i++) {
            int num = Math.abs(nums[i]);
            if (num <= n) {
                nums[num - 1] = -Math.abs(nums[num - 1]);
            }
        }

        // Tìm số dương nhỏ nhất bị thiếu
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }

        return n + 1;
    }

    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int numsLength = nums.length;
        int result = 0;
        int left = 0;
        int product = 1;

        for (int right = 0; right < numsLength; right++) {
            product *= nums[right];
            while (left <= right && product >= k) {
                product /= nums[left++];
            }
            result += right - left + 1;
        }
        return result;
    }

    public static long countSubarrays(int[] nums, int k) {
        int numsLenght = nums.length;
        int maxElement = 0;
        for (int i : nums) {
            if (maxElement < i) {
                maxElement = i;
            }
        }

        int result = 0;
        int left = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numsLenght; i++) {

        }
        return 1;
    }

    public static int[] decrypt(int[] code, int k) {
        int length = code.length;
        int decrypted[] = new int[length];
        if (k == 0) {
            Arrays.fill(decrypted, 0);
            return decrypted;
        }

        for (int i = 0; i < length; i++) {
            if (k > 0) {
                int sum = 0;
                for (int j = 1; j <= k; j++) {
                    sum += code[(i + j) % length];
                }
                decrypted[i] = sum;
            } else {
                int sum = 0;
                for (int j = -1; j >= k; j--) {
                    sum += code[(i + j + length) % length];
                }
                decrypted[i] = sum;
            }
        }

        return decrypted;
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        int strsLength = strs.length;
        if (strs == null || strsLength == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char charArray[] = str.toCharArray();
            Arrays.sort(charArray);
            String sortedSts = String.valueOf(charArray);

            if (!map.containsKey(sortedSts)) {
                map.put(sortedSts, new ArrayList<>());
            }
            map.get(sortedSts).add(str);
        }

        return new ArrayList<>(map.values());
    }

    public static boolean isNice(String s) {
        Set<Character> lowercase = new HashSet<>();
        Set<Character> uppercase = new HashSet<>();

        for (char c : s.toCharArray()) {
            if (Character.isLowerCase(c)) {
                lowercase.add(c);
            } else {
                uppercase.add(c);
            }
        }

        for (char c : lowercase) {
            char upper = Character.toUpperCase(c);
            if (!uppercase.contains(upper)) {
                return false;
            }
        }

        for (char c : uppercase) {
            char lower = Character.toLowerCase(c);
            if (!lowercase.contains(lower)) {
                return false;
            }
        }

        return true;
    }

    public static int lengthOfLastWord(String s) {
        int length = s.length();
        int lengthLastWord = 0;
        String word[] = s.split(" ");
        lengthLastWord = word[word.length - 1].length();
        return lengthLastWord;
    }

    public static String longestNiceSubstring(String s) {
        int length = s.length();
        if (s == null || length == 0) {
            return "";
        }

        String longestNice = "";
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                String substring = s.substring(i, j);
                if (isNice(substring) && longestNice.length() < substring.length()) {
                    longestNice = substring;
                }
            }
        }

        return longestNice;
    }

    public static int countConsistentStrings(String allowed, String[] words) {
        int wordsLength = words.length;
        int result = 0;
        char charAllowed[] = allowed.toCharArray();
        Set<Character> permitted = new HashSet<>();
        for (char c : charAllowed) {
            permitted.add(c);
        }

        for (String word : words) {
            boolean check = true;
            char charWord[] = word.toCharArray();
            for (char c : charWord) {
                if (!permitted.contains(c)) {
                    check = false;
                    break;
                }
            }
            if (check) {
                result++;
            }
        }

        return result;
    }

    public static boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        Map<Character, Character> isIsomorphic = new LinkedHashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (isIsomorphic.containsKey(charS)) {
                if (isIsomorphic.get(charS) != charT) {
                    return false;
                }
            } else {
                if (isIsomorphic.containsValue(charT)) {
                    return false;
                }
            }
            isIsomorphic.put(charS, charT);
        }

        return true;
    }

    public static int maxProduct(int[] nums) {
        int length = nums.length;

        if (length == 0 || nums == null) {
            return 0;
        }

        int maxProduct = nums[0];
        int minProduct = nums[0];
        int result = nums[0];

        for (int i = 1; i < length; i++) {
            int current = nums[i];
            if (current < 0) {
                int temp = maxProduct;
                maxProduct = minProduct;
                minProduct = temp;
            }

            maxProduct = Math.max(current, maxProduct * current);
            minProduct = Math.min(current, minProduct * current);

            result = Math.max(result, maxProduct);
        }

        return result;
    }

    public static String makeGood(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && Math.abs(stack.peek() - c) == 32) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder goodString = new StringBuilder();
        for (char c : stack) {
            goodString.append(c);
        }

        return String.valueOf(goodString);
    }

    public static int calPoints(String[] operations) {
        Stack<Integer> stack = new Stack<>();

        for (String operation : operations) {
            if (operation.equals("+")) {
                int top = stack.pop();
                int newTop = stack.peek() + top;
                stack.push(top);
                stack.push(newTop);
            } else if (operation.equals("D")) {
                stack.push(2 * stack.peek());
            } else if (operation.equals("C")) {
                stack.pop();
            } else {
                stack.push(Integer.parseInt(operation));
            }
        }

        int sum = 0;
        for (int score : stack) {
            sum += score;
        }

        return sum;
    }

    public static String removeDuplicates(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek().equals(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return String.valueOf(result);
    }

    public static String removeOuterParentheses(String s) {
        Stack<Character> stack = new Stack<>();
        StringBuilder result = new StringBuilder();
        int balance = 0, start = 0;

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (currentChar == '(') {
                balance++;
            } else {
                balance--;
            }

            if (balance == 0) {
                result.append(s.substring(start + 1, i));
                start = i + 1;
            }
        }

        return String.valueOf(result);
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (c == ')' && top != '(' || c == '}' && top != '{' || c == ']' && top != '[') {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    public static int minLength(String s) {
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c == 'B') {
                if (!stack.isEmpty() && stack.peek() == 'A') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else if (c == 'D') {
                if (!stack.isEmpty() && stack.peek() == 'C') {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            } else {
                stack.push(c);
            }
        }

        return stack.size();
    }

    public static String minRemoveToMakeValid(String s) {
        Stack<Integer> stack = new Stack<>();

        boolean removePosition[] = new boolean[s.length()];
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    removePosition[i] = true;
                }
            }
        }

        while (!stack.isEmpty()) {
            removePosition[stack.pop()] = true;
        }

        for (int i = 0; i < s.length(); i++) {
            if (!removePosition[i]) {
                result.append(s.charAt(i));
            }
        }

        return String.valueOf(result);
    }

    public static String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        int count = 0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == ']') {
                String temp = "";
                while (stack.peek() != '[') {
                    temp = stack.pop() + temp;
                }

                // remove '['
                stack.pop();
                String getNumber = "";
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    getNumber = stack.pop() + getNumber;
                }

                int number = Integer.valueOf(getNumber);
                temp = temp.repeat(number);

                for (int j = 0; j < temp.length(); j++) {
                    stack.push(temp.charAt(j));
                }
            } else {
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        result = result.reverse();

        return String.valueOf(result);
    }

    public static String stringAfterBackSpace(String s, Stack<Character> stack) {
        for (char c : s.toCharArray()) {
            if (c != '#') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }
        }

        String sAfterBackSpace = "";
        while (!stack.isEmpty()) {
            sAfterBackSpace = stack.pop() + sAfterBackSpace;
        }

        return sAfterBackSpace;
    }

    public static boolean backspaceCompare(String s, String t) {
        Stack<Character> stack = new Stack<>();

        String sAfterBackSpace = stringAfterBackSpace(s, stack);
        String tAfterBackSpace = stringAfterBackSpace(t, stack);

        return sAfterBackSpace.equals(tAfterBackSpace);
    }

    public static int[] finalPrices(int[] prices) {
        int length = prices.length;
        int result[] = new int[length];

        for (int i = 0; i < length - 1; i++) {
            for (int j = i + 1; j < length; j++) {
                if (prices[j] <= prices[i]) {
                    result[i] = prices[i] - prices[j];
                    break;
                } else {
                    result[i] = prices[i];
                }
            }
        }
        result[length - 1] = prices[length - 1];
        return result;
    }

    public static int[] finalPricesC2(int[] prices) {
        int length = prices.length;
        int result[] = new int[length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int index = stack.pop();
                result[index] = prices[index] - prices[i];
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int index = stack.pop();
            result[index] = prices[index];
        }

        return result;
    }

    public static int minOperations(String[] logs) {
        int length = logs.length;
        Stack<String> stack = new Stack<>();

        for (int i = 0; i < length; i++) {
            if (logs[i].equals("../")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (logs[i].equals("./")) {
                continue;
            } else {
                stack.push(logs[i]);
            }
        }

        return stack.size();
    }

    public static int maxDepth(String s) {
        Stack<Character> stack = new Stack<>();

        int maxDepth = 0;
        int currentDepth = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                currentDepth++;
                maxDepth = Math.max(currentDepth, maxDepth);
            } else if (c == ')') {
                currentDepth--;
            }
        }

        return maxDepth;
    }

    public static boolean checkValidString(String s) {
        int length = s.length();
        Stack<Integer> leftParenthesises = new Stack<>();
        Stack<Integer> starts = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            if (currentChar == '(') {
                leftParenthesises.push(i);
            } else if (currentChar == '*') {
                starts.push(i);
            } else {
                if (!leftParenthesises.isEmpty()) {
                    leftParenthesises.pop();
                } else if (!starts.isEmpty()) {
                    starts.pop();
                } else {
                    return false;
                }
            }
        }

        while (!leftParenthesises.isEmpty() && !starts.isEmpty()) {
            if (leftParenthesises.peek() > starts.peek()) {
                return false;
            }
            leftParenthesises.pop();
            starts.pop();
        }

        return leftParenthesises.isEmpty();
    }

    public static void generate(List<String> result, String current,
                                int open, int close, int n) {
        // base case: if the length of current string equals 2 * n
        if (current.length() == 2 * n) {
            result.add(current);
            return;
        }

        if (open < n) {
            generate(result, current + "(", open + 1, close, n);
        }

        if (close < open) {
            generate(result, current + ")", open, close + 1, n);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(result, "", 0, 0, n);
        return result;
    }

    public static int countStudents(int[] students, int[] sandwiches) {
        int circularPref = 0, squarePref = 0;

        for (int student : students) {
            if (student == 0) {
                circularPref++;
            } else {
                squarePref++;
            }
        }

        for (int sandwich : sandwiches) {
            if (sandwich == 0) {
                if (circularPref > 0) {
                    circularPref--;
                } else {
                    break;
                }
            } else {
                if (squarePref > 0) {
                    squarePref--;
                } else {
                    break;
                }
            }
        }

        return circularPref + squarePref;
    }

    public static int timeRequiredToBuy(int[] tickets, int k) {
        int time = 0;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tickets.length; i++) {
            if (min > tickets[i]) {
                min = tickets[i];
            }
        }
        return 1;
    }

    public static List<List<String>> solveNQueens(int n) {
        return new ArrayList<>();
    }

    public static int longestAlternatingSubarray(int[] nums, int threshold) {
        int length = nums.length;
        int left = 0, right = 0;
        int oddCount = 0, evenCount = 0;
        int maxLength = 0;

        while (right < length) {
            if (nums[right] % 2 == 0) {
                evenCount++;
            } else {
                oddCount++;
            }

            while (oddCount > 1 || nums[right] > threshold) {
                if (nums[left] % 2 == 0) {
                    evenCount--;
                } else {
                    oddCount--;
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }

    class MyStack {
        private Queue<Integer> queue1;
        private Queue<Integer> queue2;
        private int topElement;

        public MyStack() {
            queue1 = new LinkedList<>();
            queue2 = new LinkedList<>();
        }

        public void push(int x) {
            queue1.offer(x);
            topElement = x;
        }

        public int pop() {
            while (queue1.size() > 1) {
                topElement = queue1.poll();
                queue2.offer(topElement);
            }
            int popped = queue1.poll();
            Queue<Integer> temp = queue1;
            queue1 = queue2;
            queue2 = temp;

            return popped;
        }

        public int top() {
            return topElement;
        }

        public boolean empty() {
            return queue1.isEmpty();
        }
    }

    class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;

        public MyQueue() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
        }

        public int pop() {
            while (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.pop();
        }

        public int peek() {
            while (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.push(stack1.pop());
                }
            }
            return stack2.peek();
        }

        public boolean empty() {
            return stack1.isEmpty() && stack2.isEmpty();
        }
    }

    public static String simplifyPath(String path) {
        //   /a/./b/../../c/

        Stack<String> stack = new Stack<>();
        String parts[] = path.split("/");

        for (String part : parts) {
            System.out.println(part);
        }

        for (String part : parts) {
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else if (!part.equals(".") && !part.isEmpty()) {
                stack.push(part);
            }
        }

        StringBuilder result = new StringBuilder("/");
        for (String dir : stack) {
            result.append(dir).append("/");
        }

        if (result.length() > 1) {
            result.delete(result.length() - 1, result.length());
        }

        return String.valueOf(result);
    }

    public static int scoreOfParentheses(String s) {
        // nghi cach khac
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(0);
            } else {
                int current = stack.pop();
                int previous = stack.pop();
                stack.push(previous + Math.max(current * 2, 1));
            }
        }

        return stack.pop();
    }

    public static String removeStars(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        for (char c : s.toCharArray()) {
            if (c != '*') {
                stack.push(c);
            } else {
                stack.pop();
            }
        }

        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb = sb.reverse();

        return String.valueOf(sb);
    }

    public static void backtrack(int nums[], List<List<Integer>> result,
                                 List<Integer> current, boolean used[]) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                current.add(nums[i]);
                used[i] = true;
                backtrack(nums, result, current, used);
                current.remove(current.size() - 1);
                used[i] = false;
            }
        }
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean used[] = new boolean[nums.length];
        backtrack(nums, result, current, used);
        return result;
    }

    public static int[] deckRevealedIncreasing(int[] deck) {
        int length = deck.length;
        Arrays.sort(deck);

        Queue<Integer> indexQueue = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            indexQueue.offer(i);
        }

        int result[] = new int[length];
        for (int card : deck) {
            result[indexQueue.poll()] = card;
            if (!indexQueue.isEmpty()) {
                indexQueue.offer(indexQueue.poll());
            }
        }

        return result;
    }

    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        boolean visited[] = new boolean[26];
        int count[] = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        for (char c : s.toCharArray()) {
            count[c - 'a']--;
            if (visited[c - 'a']) {
                continue;
            }

            while (!stack.isEmpty() && c < stack.peek() && count[stack.peek() - 'a'] > 0) {
                visited[stack.pop() - 'a'] = false;
            }

            stack.push(c);
            visited[c - 'a'] = true;
        }

        StringBuilder result = new StringBuilder();
        for (char c : stack) {
            result.append(c);
        }

        return result.toString();
    }

    public static boolean buddyStrings(String s, String goal) {
        if (s.length() != goal.length()) {
            return false;
        }

        Map<Character, Integer> frequency = new HashMap<>();
        int countGreaterTwo = 0;
        for (char c : s.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
            if (frequency.get(c) >= 2) {
                countGreaterTwo++;
            }
        }

        if (s.equals(goal)) {
            if (countGreaterTwo >= 1) {
                return true;
            } else {
                return false;
            }
        }

        char tempS = ' ', tempGoal = ' ';
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char goalChar = goal.charAt(i);
            if (sChar != goalChar) {
                count++;
            }
        }
        if (count > 2 || count == 1) {
            return false;
        }

        int dem = 0;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char sChar = s.charAt(i);
            char goalChar = goal.charAt(i);

            if (sChar != goalChar) {
                dem++;
                if (map.isEmpty()) {
                    tempS = sChar;
                    tempGoal = goalChar;
                }
                map.put(sChar, goalChar);
                if (!map.isEmpty() && !map.get(sChar).equals(tempS) && dem == 2) {
                    return false;
                }

                if (!map.isEmpty() && sChar != tempGoal && dem == 2) {
                    return false;
                }
            }
        }

        return true;
    }

    public static int uniqueMorseRepresentations(String[] words) {
        final String MORSE_CODE[] = {".-", "-...", "-.-.", "-..", ".", "..-.", "--.",
                "....", "..", ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-",
                ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};

        Set<String> transformations = new HashSet<>();
        for (String word : words) {
            StringBuilder transformation = new StringBuilder();

            for (char c : word.toCharArray()) {
                transformation.append(MORSE_CODE[c - 'a']);
            }

            transformations.add(transformation.toString());
        }

        return transformations.size();
    }

    public static String shortestCompletingWord(String licensePlate, String[] words) {
        String digit = licensePlate.replaceAll("[^a-zA-Z]", "").toLowerCase();
        String shortestWord = null;

        Map<Character, Integer> frequency = new HashMap<>();
        for (char c : digit.toCharArray()) {
            frequency.put(c, frequency.getOrDefault(c, 0) + 1);
        }

        for (String word : words) {
            if (shortestWord != null && word.length() >= shortestWord.length()) {
                continue;
            }

            Map<Character, Integer> remainingChars = new HashMap<>(frequency);
            for (char c : word.toCharArray()) {
                if (remainingChars.containsKey(c)) {
                    remainingChars.put(c, remainingChars.get(c) - 1);
                    if (remainingChars.get(c) == 0) {
                        remainingChars.remove(c);
                    }
                }
            }

            if (remainingChars.isEmpty()) {
                shortestWord = word;
            }
        }

        return shortestWord;
    }

    public static int[] shortestToChar(String s, char c) {
        int length = s.length();
        int result[] = new int[length];

        int left = -1, right = -1;
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == c) {
                left = i;
            }

            if (left != -1) {
                result[i] = i - left;
            } else {
                result[i] = Integer.MAX_VALUE;
            }
        }

        for (int i = length - 1; i >= 0; i--) {
            if (s.charAt(i) == c) {
                right = i;
            }

            if (right != -1) {
                result[i] = Math.min(result[i], right - i);
            }
        }

        return result;
    }

    static class alienComparator implements Comparator<String> {
        private String order;

        public alienComparator(String order) {
            this.order = order;
        }

        @Override
        public int compare(String left, String right) {
            int minLength = Math.min(left.length(), right.length());
            for (int i = 0; i < minLength; i++) {
                char charLeft = left.charAt(i);
                char charRight = right.charAt(i);
                int indexLeft = order.indexOf(charLeft);
                int indexRight = order.indexOf(charRight);
                if (indexLeft != indexRight) {
                    return indexLeft - indexRight;
                }
            }
            return left.length() - right.length();
        }
    }

    public static boolean isAlienSorted(String[] words, String order) {
        String copied[] = words.clone();
        Arrays.sort(copied, new alienComparator(order));

        return Arrays.equals(words, copied);
    }

    public static boolean isAlienSortedC2(String[] words, String order) {
        Map<Character, Integer> orderMap = new HashMap<>();
        for (int i = 0; i < order.length(); i++) {
            orderMap.put(order.charAt(i), i);
        }

        String copied[] = words.clone();
        Arrays.sort(copied, (a, b) -> {
            int minLength = Math.min(a.length(), b.length());
            for (int i = 0; i < minLength; i++) {
                char charLeft = a.charAt(i);
                char charRight = b.charAt(i);
                int indexLeft = orderMap.get(charLeft);
                int indexRight = orderMap.get(charRight);
                if (indexRight != indexLeft) {
                    return indexLeft - indexRight;
                }
            }
            return a.length() - b.length();
        });

        return Arrays.equals(words, copied);
    }

    public static void backtrack(List<List<String>> result, List<String> tempList,
                                 String s, int start) {
        if (start == s.length()) {
            result.add(new ArrayList<>(tempList));
        } else {
            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {
                    tempList.add(s.substring(start, i + 1));
                    backtrack(result, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    public static List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        List<String> tempList = new ArrayList<>();
        backtrack(result, tempList, s, 0);
        return result;
    }

    public static int minCut(String s) {
        int length = s.length();
        int dp[] = new int[length];
        boolean isPalindrome[][] = new boolean[length][length];

        // Initialize dp array with maximum possible cuts for each index
        for (int i = 0; i < length; i++) {
            dp[i] = i;
        }

        for (int end = 0; end < length; end++) {
            for (int start = 0; start <= end; start++) {
                if (s.charAt(start) == s.charAt(end) &&
                        (end - start <= 1 || isPalindrome[start + 1][end - 1])) {
                    isPalindrome[start][end] = true;
                    if (start == 0) {
                        dp[end] = 0;
                    } else {
                        dp[end] = Math.min(dp[end], dp[start - 1] + 1);
                    }
                }
            }
        }

        return dp[length - 1];
    }

    private String result = "";
    private int count = 0;

    public void backtracking(int n, int k, String permutation, boolean used[]) {
        if (permutation.length() == n) {
            count++;
            if (count == k) {
                result = permutation;
            }
            return;
        }

        for (int i = 1; i <= n; i++) {
            if (!used[i]) {
                used[i] = true;
                backtracking(n, k, permutation + i, used);
                used[i] = false;
            }
        }
    }

    public String getPermutation(int n, int k) {
        boolean used[] = new boolean[n + 1];
        backtracking(n, k, "", used);

        return result;
    }

    public static void backtrackUnique(int nums[], List<List<Integer>> result,
                                       List<Integer> current, boolean used[]) {
        if (current.size() == nums.length) {
            List<Integer> temp = new ArrayList<>(current);
            if (!result.contains(temp)) {
                result.add(temp);
            }
        } else {
            for (int i = 0; i < nums.length; i++) {
                if (!used[i]) {
                    used[i] = true;
                    current.add(nums[i]);
                    backtrackUnique(nums, result, current, used);
                    current.remove(current.size() - 1);
                    used[i] = false;
                }
            }
        }
    }

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean used[] = new boolean[nums.length];
        backtrackUnique(nums, result, new ArrayList<>(), used);

        return result;
    }

    public void backtrack(StringBuilder combination, List<String> result, String digits,
                          Map<Character, String> map, int index) {
        if (index == digits.length()) {
            result.add(combination.toString());
            return;
        }

        char digit = digits.charAt(index);
        String leters = map.get(digit);
        for (int i = 0; i < leters.length(); i++) {
            combination.append(leters.charAt(i));
            backtrack(combination, result, digits, map, index + 1);
            combination.deleteCharAt(combination.length() - 1);
        }
    }

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.length() == 0) {
            return result;
        }

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        backtrack(new StringBuilder(), result, digits, map, 0);
        return result;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> combination, int candidates[],
                          int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
            combination.add(candidates[i]);
            backtrack(result, combination, candidates, target - candidates[i], i);
            combination.remove(combination.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(result, new ArrayList<>(), candidates, target, 0);

        return result;
    }

    public void backtrackSum2(List<List<Integer>> result, List<Integer> combination, int candidates[],
                              int target, int start) {
        if (target == 0) {
            result.add(new ArrayList<>(combination));
            return;
        }

        for (int i = start; i < candidates.length && candidates[i] <= target; i++) {
            if (i > start && candidates[i] == candidates[i - 1]) {
                continue;
            }
            combination.add(candidates[i]);
            backtrackSum2(result, combination, candidates, target - candidates[i], i + 1);
            combination.remove(combination.size() - 1);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        backtrackSum2(result, new ArrayList<>(), candidates, target, 0);

        return result;
    }

    public int backtrackSum4(int nums[], int target, Map<Integer, Integer> memory) {
        if (target == 0) {
            return 1;
        }

        if (memory.containsKey(target)) {
            return memory.get(target);
        }

        int count = 0;
        for (int num : nums) {
            if (num <= target) {
                count += backtrackSum4(nums, target - num, memory);
            }
        }

        memory.put(target, count);
        return count;
    }


    public int combinationSum4(int[] nums, int target) {
        return backtrackSum4(nums, target, new HashMap<>());
    }

    public int combinationSum4DP(int[] nums, int target) {
        int dp[] = new int[target + 1];
        dp[0] = 1;

        // dp[i] tính toan so cach ket hop de tao ra tong i
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i - num >= 0) {
                    dp[i] += dp[i - num];
                }
            }
        }

        return dp[target];
    }

    public int tribonacci(int n) {
        int fibo[] = new int[92];
        fibo[0] = 0;
        fibo[1] = 1;
        fibo[2] = 1;

        for (int i = 3; i <= n; i++) {
            fibo[i] = fibo[i - 3] + fibo[i - 2] + fibo[i - 1];
        }

        return fibo[n];
    }

    public String reverseWords(String s) {
        s = s.trim();
        s = s.replaceAll("\\s+", " ");
        String arrString[] = s.split(" ");
        int left = 0, right = arrString.length - 1;
        while (left < right) {
            String temp = arrString[left];
            arrString[left] = arrString[right];
            arrString[right] = temp;
            left++;
            right--;
        }

        StringBuilder result = new StringBuilder();
        for (String str : arrString) {
            result.append(str).append(" ");
        }

        result.deleteCharAt(result.length() - 1);
        return String.valueOf(result);
    }

    private boolean isValidSegment(String segment) {
        if (segment.length() > 1 && segment.startsWith("0")) {
            return false;
        }
        int value = Integer.parseInt(segment);

        return value >= 0 && value <= 255;
    }

    public void backtrack(List<String> result, String s, List<String> current, int start) {
        if (current.size() == 4) {
            if (start == s.length()) {
                result.add(String.join(".", current));
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            if (start + i > s.length()) {
                break;
            }

            String segment = s.substring(start, start + i);
            if (isValidSegment(segment)) {
                current.add(segment);
                backtrack(result, s, current, start + i);
                current.remove(current.size() - 1);
            }
        }
    }

    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, s, new ArrayList<>(), 0);

        return result;
    }

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;

        for (int num : pushed) {
            stack.push(num);
            while (!stack.isEmpty() && stack.peek() == popped[index]) {
                stack.pop();
                index++;
            }
        }

        return stack.isEmpty();
    }

    public String convert(String s, int numRows) {

        return "";
    }

    public int numDistinct(String s, String t) {
        int length = s.length();

        return 1;
    }

    public boolean isVowel(char c) {
        c = Character.toLowerCase(c);
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    public String toGoatLatin(String sentence) {
        StringBuilder result = new StringBuilder();
        String words[] = sentence.split(" ");
        int index = 1;

        for (String word : words) {
            if (isVowel(word.charAt(0))) {
                result.append(word).append("ma");
            } else {
                result.append(word.substring(1)).append(word.charAt(0)).append("ma");
            }

            for (int i = 0; i < index; i++) {
                result.append("a");
            }

            result.append(" ");
            index++;
        }

        return result.toString().trim();
    }

    private void backtrack(Map<Character, Integer> map, String sequence, List<String> result) {
        for (char c : map.keySet()) {
            int count = map.get(c);
            if (count == 0) {
                continue;
            }
            map.put(c, count - 1);
            String newSequence = sequence + c;
            result.add(newSequence);
            backtrack(map, sequence, result);
            map.put(c, count);
        }
    }

    public int numTilePossibilities(String tiles) {
        Map<Character, Integer> frequency = new HashMap<>();
        for (char title : tiles.toCharArray()) {
            frequency.put(title, frequency.getOrDefault(title, 0) + 1);
        }

        List<String> result = new ArrayList<>();
        backtrack(frequency, "", result);

        return result.size();
    }

    public List<List<Integer>> largeGroupPositions(String s) {
        List<List<Integer>> result = new ArrayList<>();

        for (int left = 0; left < s.length(); left++) {
            int right = left + 1;
            if (left > 0 && s.charAt(left) == s.charAt(left - 1)) {
                continue;
            }
            int count = 1;
            while (right < s.length() && s.charAt(left) == s.charAt(right)) {
                right++;
                count++;
            }

            if (count >= 3) {
                List<Integer> interval = new ArrayList<>();
                interval.add(left);
                interval.add(right - 1);
                result.add(interval);
            }
        }

        return result;
    }

    public int minDeletionSize(String[] strs) {
        int numColsToDelete = 0;
        int numRows = strs.length;
        int numCols = strs[0].length();

        for (int j = 0; j < numCols; j++) {
            for (int i = 0; i < numRows - 1; i++) {
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    numColsToDelete++;
                    break;
                }
            }
        }

        return numColsToDelete;
    }

    public int[] diStringMatch(String s) {
        int length = s.length();
        int perm[] = new int[length + 1];

        List<Integer> range = new ArrayList<>();
        for (int i = 0; i <= s.length(); i++) {
            range.add(i);
        }

        for (int i : range) {
            System.out.println(i);
        }
        for (int i = 0; i < length; i++) {
            if (s.charAt(i) == 'I') {
                perm[i] = range.get(0);
                range.remove(0);
            } else {
                perm[i] = range.get(range.size() - 1);
                range.remove(range.size() - 1);
            }
        }

        perm[s.length()] = range.get(0);
        return perm;
    }

    public int[] diStringMatchC2(String s) {
        int length = s.length();
        int perm[] = new int[length + 1];
        char charArr[] = s.toCharArray();
        int left = 0, right = length;
        int index = 0;

        while (left < right) {
            if (charArr[index] == 'I') {
                perm[index] = left;
                left++;
            } else {
                perm[index] = right;
                right--;
            }

            index++;
        }

        if (charArr[length - 1] == 'I') {
            perm[index] = left;
        } else {
            perm[index] = right;
        }

        return perm;
    }

    public String[] findOcurrences(String text, String first, String second) {
        String occurence[] = new String[2];

        String word[] = text.split(" ");
        int countFirst = 0, countSecond = 0;
        for (int i = 0; i < word.length; i++) {
            if (word[i].equals(first)) {
                countFirst++;
            } else if (word[i].equals(second)) {
                countSecond++;
            }

            if (countFirst == 1 && word[i].equals(first)) {
                occurence[0] = word[i + 2];
            }
            if (countSecond == 2 && word[i].equals(second)) {
                occurence[1] = word[i + 1];
            }
        }

        return occurence;
    }

    public void backtrack(List<List<Integer>> result, List<Integer> list, int k, int n, int start) {
        if (k == 0) {
            result.add(new ArrayList<>(list));
            return;
        }

        for (int i = start; i <= n; i++) {
            list.add(i);
            backtrack(result, list, k - 1, n, i + 1);
            list.remove(list.size() - 1);
        }
    }

    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), k, n, 1);

        return result;
    }

    public int minFallingPathSumHard(int[][] a) {
        int length = a.length;

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int minPreRow = Integer.MAX_VALUE;
                for (int k = 0; k < length; k++) {
                    if (k != j) {
                        minPreRow = Math.min(minPreRow, a[i - 1][k]);
                    }
                }
                a[i][j] += minPreRow;
            }
        }

        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < length; j++) {
            if (minSum > a[length - 1][j]) {
                minSum = a[length - 1][j];
            }
        }

        return minSum;
    }

    public int minFallingPathSumMedium(int[][] a) {
        int length = a.length;

        for (int i = 1; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (j == 0) {
                    a[i][j] += Math.min(a[i - 1][j], a[i - 1][j + 1]);
                } else if (j == length - 1) {
                    a[i][j] += Math.min(a[i - 1][j - 1], a[i - 1][j]);
                } else {
                    a[i][j] += Math.min(a[i - 1][j], Math.min(a[i - 1][j - 1], a[i - 1][j + 1]));
                }
            }
        }

        int minSum = Integer.MAX_VALUE;
        for (int j = 0; j < length; j++) {
            if (minSum > a[length - 1][j]) {
                minSum = a[length - 1][j];
            }
        }

        return minSum;
    }

    public int uniquePathsWithObstacles(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;

        int dp[][] = new int[numRows][numCols];
        dp[0][0] = 1;

        if (grid[0][0] == 1 || grid[numRows - 1][numCols - 1] == 1) {
            return 0;
        }

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] != 1) {
                    if (i > 0) {
                        dp[i][j] += grid[i][j];
                    }
                    if (j > 0) {
                        dp[i][j] += grid[i][j];
                    }
                }
            }
        }

        return dp[numRows - 1][numCols - 1];
    }

    class MyHashMap {
        private final int SIZE = 10000;
        private Node[] map;

        private class Node {
            int key;
            int value;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        public MyHashMap() {
            map = new Node[SIZE];
        }

        public void put(int key, int value) {

        }

        public int get(int key) {
            return 1;
        }

        public void remove(int key) {
        }

        public Node find() {
            return new Node(10, 1);
        }
    }

    public int uniquePathsIII(int[][] grid) {
        int numRows = grid.length;
        int numCols = grid[0].length;
        int walkNums = 0;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == 1) {

                }
            }
        }

        return walkNums;
    }

    public String defangIPaddr(String address) {
        address = address.replace(".", "[.]");
        return address;
    }

    public int balancedStringSplit(String s) {
        int maxNumber = 0;
        int countL = 0, countR = 0;

        for (char c : s.toCharArray()) {
            if (c == 'L') {
                countL++;
            } else {
                countR++;
            }

            if (countL == countR) {
                maxNumber++;
            }
        }

        return maxNumber;
    }

    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    public int removePalindromeSub(String s) {
        if (s.isEmpty()) {
            return 0;
        }

        if (isPalindrome(s)) {
            return 1;
        }

        return 2;
    }

    public String gcdOfStrings(String str1, String str2) {
        String result = "";
        if (!str1.contains(str2)) {
            return "";
        }

        int index = 0;
        for (int i = 0; i < str1.length(); i++) {
            if (i == str2.length()) {
                index = i;
                break;
            }
        }

        str1 = str1.substring(index);
        if (!str2.contains(str1)) {
            return "";
        }

        return str1;
    }

    public int myAtoi(String s) {
        s = s.replace("\\s+", " ");
        String words[] = s.split(" ");
        String result = "";
        if (Character.isLetter(words[0].charAt(0))) {
            return 0;
        }
        int count = -1;
        for (String word : words) {
            count++;
            boolean check = true;
            for (int i = 0; i < word.toCharArray().length; i++) {
//                if (count == 0 && Character.isLetter(word.charAt(0))) {
//                    return 0;
//                }
                System.out.println(1);
                if (word.charAt(i) == '+' || word.charAt(i) == '-') {
                    continue;
                }

                if (!Character.isDigit(word.charAt(i))) {
                    check = false;
                    break;
                }
            }
            if (check) {
                result += word;
            }
        }

        String temp = result;
        long integer = Integer.parseInt(result.toString());
        if (integer < Math.pow(-2, -31)) {
            return (int) Math.pow(-2, -31);
        } else if (integer > Math.pow(2, 31) - 1) {
            return (int) Math.pow(2, 31) - 1;
        }

        return Integer.parseInt(temp);
    }

    public String reverse(String s, int left, int right) {
        char charArr[] = s.toCharArray();

        while (left < right) {
            char temp = charArr[left];
            charArr[left] = charArr[right];
            charArr[right] = temp;

            left++;
            right--;
        }

        return new String(charArr);
    }

    public String reversePrefix(String word, char ch) {
        int firstIdx = word.indexOf(ch);
        word = reverse(word, 0, firstIdx);

        return word;
    }

    public int countCharacters(String[] words, String chars) {
        int length = words.length;
        Set<String> result = new HashSet<>();

        System.out.println(chars);
        int maxLength = 0;
        for (String word : words) {
            char charArray[] = word.toCharArray();
            Arrays.sort(charArray);
            String temp = String.valueOf(charArray);

            if (chars.contains(temp)) {
                maxLength += temp.length();
            }
        }

        return maxLength;
    }

    public int compareVersion(String version1, String version2) {
        String ver1List[] = version1.split("\\.");
        String ver2List[] = version2.split("\\.");

        int maxLength = Math.max(ver2List.length, ver1List.length);

        for (int i = 0; i < maxLength; i++) {
            int num1 = i < ver1List.length ? Integer.parseInt(ver1List[i]) : 0;
            int num2 = i < ver2List.length ? Integer.parseInt(ver2List[i]) : 0;

            if (num1 < num2) {
                return -1;
            } else if (num1 > num2) {
                return 1;
            }
        }

        return 0;
    }

    public int myAtoiGPT(String s) {
        int index = 0;
        int sign = 1;
        int result = 0;

        // Remove leading whitespace
        while (index < s.length() && s.charAt(index) == ' ') {
            index++;
        }

        // Check sign
        if (index < s.length() && (s.charAt(index) == '-' || s.charAt(index) == '+')) {
            sign = (s.charAt(index++) == '-') ? -1 : 1;
        }

        // Convert digits
        while (index < s.length() && Character.isDigit(s.charAt(index))) {
            int digit = s.charAt(index++) - '0';

            // Check for overflow
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
        }

        return result * sign;
    }

    public void reverseRow(int row[]) {
        int length = row.length;


    }

    public int[][] flipAndInvertImage(int[][] image) {
        int row = image.length;
        int col = image[0].length;

        for (int i = 0; i < row; i++) {
            int left = 0, right = row - 1;
            for (int j = 0; j < col; j++) {
                while (left < right) {
                    int temp = image[i][left];
                    image[i][left] = image[i][right];
                    image[i][right] = temp;
                    left++;
                    right--;
                }
            }
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (image[i][j] == 0) {
                    image[i][j] = 1;
                } else {
                    image[i][j] = 0;
                }
            }
        }

        return image;
    }

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0, right = people.length - 1;
        int boast = 0;

        while (left <= right) {
            if (people[left] + people[right] <= limit) {
                left++;
            }
            right--;
            boast++;
        }

        return boast;
    }

    public boolean isValidSudoku(char[][] board) {
        Map<Integer, Integer> rows[] = new HashMap[9];
        Map<Integer, Integer> cols[] = new HashMap[9];
        Map<Integer, Integer> boxes[] = new HashMap[9];

        for (int i = 0; i < 9; i++) {
            rows[i] = new HashMap<>();
            cols[i] = new HashMap<>();
            boxes[i] = new HashMap<>();
        }

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                char num = board[i][j];
                if (num != '.') {
                    int digit = num - '0';
                    int boxIndex = i / 3 * 3 + j / 3;

                    rows[i].put(digit, rows[i].getOrDefault(digit, 0) + 1);
                    if (rows[i].get(digit) > 1) {
                        return false;
                    }

                    cols[j].put(digit, cols[j].getOrDefault(digit, 0) + 1);
                    if (cols[j].get(digit) > 1) {
                        return false;
                    }

                    boxes[boxIndex].put(digit, boxes[boxIndex].getOrDefault(digit, 0) + 1);
                    if (boxes[boxIndex].get(digit) > 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    public boolean checkValid(int[][] matrix) {
        int length = matrix.length;
        Map<Integer, Integer> rows[] = new HashMap[length];
        Map<Integer, Integer> cols[] = new HashMap[length];

        for (int i = 0; i < length; i++) {
            rows[i] = new HashMap<>();
            cols[i] = new HashMap<>();
        }

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                int num = matrix[i][j];

                rows[i].put(num, rows[i].getOrDefault(num, 0) + 1);
                if (rows[i].get(num) > 1) {
                    return false;
                }

                cols[j].put(num, cols[j].getOrDefault(num, 0) + 1);
                if (cols[j].get(num) > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean checkValidGPT(int[][] matrix) {
        int lenght = matrix.length;

        for (int i = 0; i < lenght; i++) {
            Set<Integer> rowSet = new HashSet<>();
            for (int j = 0; j < lenght; j++) {
                int num = matrix[i][j];
                if (num < 0 || num > lenght || !rowSet.add(num)) {
                    return false;
                }
            }
        }

        for (int i = 0; i < lenght; i++) {
            Set<Integer> colSet = new HashSet<>();
            for (int j = 0; j < lenght; j++) {
                int num = matrix[j][i];
                if (num < 0 || num > lenght || !colSet.add(num)) {
                    return false;
                }
            }
        }

        return true;
    }

    public int[] reverseArray(int array[]) {
        int left = 0, right = array.length - 1;
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
        return array;
    }

    public String[] findRelativeRanks(int[] score) {
        int length = score.length;
        int copiedScore[] = score.clone();
        Arrays.sort(copiedScore);
        copiedScore = reverseArray(copiedScore);

        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                map.put(copiedScore[i], "Gold Medal");
            } else if (i == 1) {
                map.put(copiedScore[i], "Silver Medal");
            } else if (i == 2) {
                map.put(copiedScore[i], "Bronze Medal");
            } else {
                map.put(copiedScore[i], i + 1 + "");
            }
        }

        List<String> list = new ArrayList<>();
        for (int i = 0; i < length; i++) {
            list.add(map.get(score[i]));
        }

        return list.toArray(new String[0]);
    }

    public void solveSudoku(char[][] board) {

    }

    public void backtrack(List<List<Integer>> result, List<Integer> currentList, int nums[], int start) {
        result.add(new ArrayList<>(currentList));

        for (int i = start; i < nums.length; i++) {
            currentList.add(nums[i]);
            backtrack(result, currentList, nums, i + 1);
            currentList.remove(currentList.size() - 1);
        }
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);

        for (List<Integer> list : result) {
            System.out.println(list);
        }
        return result;
    }

    public void backtrack(List<List<Integer>> result, int start, List<Integer> currentList, int nums[]) {
        if (!result.contains(new ArrayList<>(currentList))) {
            result.add(new ArrayList<>(currentList));
        }

        for (int i = start; i < nums.length; i++) {
            currentList.add(nums[i]);
            backtrack(result, i + 1, currentList, nums);
            currentList.remove(currentList.size() - 1);
        }
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, 0, new ArrayList<>(), nums);
        return result;
    }

    public void backtrack(List<String> result, char[] chars, int index) {
        if (index == chars.length) {
            result.add(new String(chars));
            return;
        }

        char c = chars[index];
        if (Character.isLetter(c)) {
            chars[index] = Character.toLowerCase(c);
            backtrack(result, chars, index + 1);

            chars[index] = Character.toUpperCase(c);
            backtrack(result, chars, index + 1);
        } else {
            backtrack(result, chars, index + 1);
        }
    }

    public List<String> letterCasePermutation(String s) {
        List<String> result = new ArrayList<>();
        backtrack(result, s.toCharArray(), 0);
        return result;
    }

    public int[][] largestLocal(int[][] grid) {
        int dx[] = {0, 0, 0, 1, 1, 1, 2, 2, 2};
        int dy[] = {0, 1, 2, 0, 1, 2, 0, 1, 2};

        int length = grid.length;
        int maxLocal[][] = new int[length - 2][length - 2];

        for (int i = 0; i < length - 2; i++) {
            for (int j = 0; j < length - 2; j++) {
                int maxValue = Integer.MIN_VALUE;
                for (int k = 0; k < 9; k++) {
                    maxValue = Math.max(maxValue, grid[dx[k] + i][dy[k] + j]);
                }
                maxLocal[i][j] = maxValue;
            }
        }

        return maxLocal;
    }

    public void backtrack(int index, List<List<Integer>> result, List<Integer> currentList, int nums[]) {
        if (currentList.size() >= 2) {
            result.add(new ArrayList<>(currentList));
        }

        Set<Integer> used = new HashSet<>();
        for (int i = index; i < nums.length; i++) {
            if (used.contains(nums[i])) {
                continue;
            }
            if (currentList.size() > 0 && nums[i] < currentList.get(currentList.size() - 1)) {
                continue;
            }

            currentList.add(nums[i]);
            used.add(nums[i]);
            backtrack(i + 1, result, currentList, nums);
            currentList.remove(currentList.size() - 1);
        }
    }

    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, result, new ArrayList<>(), nums);

        return result;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }

        int result = dividend / divisor;
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        } else if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        return result;
    }

    public void nextPermutation(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();
        boolean used[] = new boolean[arr.length];
        backtrack(arr, result, current, used);

        List<Integer> temp = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).equals(arr)) {
                temp.addAll(result.get(i + 1));
                break;
            }
        }

        for (int i : temp) {
            System.out.println(temp);
        }
        for (int i = 0; i < arr.length; i++) {
            arr[i] = temp.get(i);
        }
    }

    public int backtrack(int grid[][], int row, int col, boolean visited[][]) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length
                || visited[row][col] || grid[row][col] == 0) {
            return 0;
        }

        visited[row][col] = true;
        int gold = grid[row][col];

        int maxGold = gold + Math.max(
                Math.max(backtrack(grid, row - 1, col, visited), backtrack(grid, row + 1, col, visited)),
                Math.max(backtrack(grid, row, col - 1, visited), backtrack(grid, row, col + 1, visited))
        );

        visited[row][col] = false;

        return maxGold;
    }

    public int getMaximumGold(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int maxGold = 0;


        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] > 0) {
                    boolean visited[][] = new boolean[row][col];
                    maxGold = Math.max(maxGold, backtrack(grid, i, j, visited));
                }
            }
        }

        return maxGold;
    }

    public long maximumHappinessSum(int[] happiness, int k) {
        Arrays.sort(happiness);
        int length = happiness.length;
        long maxSum = 0;

        for (int i = length - 1; i >= 0; i--) {
            boolean check = false;
            if (k == 0) {
                break;
            }
            maxSum += happiness[i];
            System.out.println(happiness[i]);
            for (int j = 0; j < i; j++) {
                happiness[j] -= 1;
                if (happiness[j] == 0) {
                    check = true;
                    break;
                }
            }
            if (check) {
                break;
            }
            k--;
        }

        return maxSum;
    }

    public long maximumHappinessSumGpt(int[] happiness, int k) {
        Arrays.sort(happiness);
        int length = happiness.length;
        long maxSum = 0;

        for (int i = length - 1; i >= 0; i--) {
            if (k == 0 || happiness[i] == 0) {
                break;
            }
            maxSum += happiness[i];
            for (int j = 0; j < i; j++) {
                happiness[j] -= 1;
            }
            k--;
        }

        return maxSum;
    }

    public int numSteps(String s) {
        long decimal = Long.parseLong(s, 2);
        System.out.println(decimal);
        int step = 0;
        while (decimal != 1) {
            while (decimal % 2 == 0) {
                decimal /= 2;
                step++;
            }

            while (decimal % 2 == 1 && decimal != 1) {
                decimal += 1;
                step++;
            }
        }

        return step;
    }

    public int[] singleNumber(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for (int i : nums) {
            frequency.put(i, frequency.getOrDefault(i, 0) + 1);
        }

        int result[] = new int[2];
        int k = 0;
        for (Map.Entry<Integer, Integer> entry : frequency.entrySet()) {
            if (entry.getValue() == 1) {
                result[k++] = entry.getKey();
            }
        }
        return result;
    }

    public int totalNQueens(int n) {
        // backtracking
        int queens[] = new int[100];
        int count = 0;
        int d1[] = new int[100];
        int d2[] = new int[100];

        for (int i = 1; i <= n; i++) {

        }
        return 1;
    }

    public List<String> commonChars(String[] words) {
        List<String> result = new ArrayList<>();
        int length = words.length;
        Map<Character, Integer> frequency = new HashMap<>();

        for (int i = 0; i < length; i++) {
            String word = words[i];
            
        }

        return result;
    }

    public boolean isNStraightHand(int[] hand, int groupSize) {
        if (hand.length != groupSize * groupSize) {
            return false;
        }
        return true;
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        String result = "";
        Set<String> set = new HashSet<>(dictionary);
        String words[] = sentence.split(" ");

        for (String word : words) {
            String temp = "";
            boolean check = false;
            for (String s : set) {
                if (word.contains(s)) {
                    temp = s;
                    check = true;
                    break;
                }
            }
            if (!check) {
                result += word + " ";
            } else {
                result += temp + " ";
            }
        }
        return result.trim();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "1101";
        System.out.println(solution.numSteps(s));
    }
}
