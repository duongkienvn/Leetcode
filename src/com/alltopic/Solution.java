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
            if (s.charAt(left) != s.charAt(right)) return false;
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

        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);

            if (current == '[') {
                stack.push(current);
            } else if (current == ']') {
                
            }
        }
        return "";
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


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums6 = {2, 0, 3, -2, 4};
        System.out.println("Test case 6: " + maxProduct(nums6));

    }
}
