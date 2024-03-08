package Sorting;

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
            if (isSubsequence(word, s))
                return word;
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
        int result[] = new int[row];
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
                if (half > i)
                    cnt += half - i;
                else if (half < i)
                    cnt += i - half;
            }

        } else {
            half++;
            for (int i : nums) {
                if (half > i)
                    cnt += half - i;
                else if (half < i)
                    cnt += i - half;
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
            if (c == '1')
                countOne++;
            else
                countZero++;
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

    public static void swapCharacter(char charArray[], int left, int right) {
        char temp = charArray[left];
        charArray[left] = charArray[right];
        charArray[right] = temp;
    }

    public static String reverseVowels(String s) {
        // reverse vowels of string
        char chars[] = s.toCharArray();
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
        int result[] = new int[numsLength];

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
        int result[] = new int[numsLength];

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
            if (s.charAt(left) != s.charAt(right))
                return false;
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
                return isPalindrome(s, left + 1, right)
                        || isPalindrome(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    public static String reverseOnlyLetters(String s) {
        char chars[] = s.toCharArray();
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
            } else if (pointerTyped > 0 && typed.charAt(pointerTyped) == typed.charAt(pointerTyped - 1))
                pointerTyped++;
            else
                return false;
        }

        return pointerName == nameLength;
    }

    public static void duplicateZeros(int[] arr) {
        int n = arr.length;
        int zeros = 0;

        for (int num : arr) {
            if (num == 0)
                zeros++;
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
        if (power < tokens[0])
            return 0;

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

    public static int rightToLeft(int n){
        if(n == 1)
            return 1;
        if(n % 2 == 1)
            return 2 * leftToRight(n / 2);
        return 2 * leftToRight(n / 2) - 1;
    }

    public static int leftToRight(int n){
        if(n == 1)
            return 1;
        return 2 * rightToLeft(n / 2);
    }
    public static int lastRemaining(int n) {
        return leftToRight(n);
    }

    public static boolean isSymmetrical(String s){
        int left = 0, right = s.length() - 1;
        while(left < right){
            if(s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
    public static int minimumLength(String s) {

        int left = 0, right = s.length() - 1;
        while(left < right && s.charAt(left) == s.charAt(right)){
            char currentChar = s.charAt(left);
            while(left <= right && s.charAt(left) == currentChar){
                left++;
            }
            while(left <= right && s.charAt(right) == currentChar){
                right--;
            }
        }
        return right - left + 1;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        List<Integer> result = new ArrayList<>();
        int arrLength = arr.length;
        for(int i = 0; i < arrLength; i++){

        }
        return result;
    }

    public static String customSortString(String order, String s) {
        String result = "";
        String tmp = "";
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for(char c: order.toCharArray()){
            set.add(c);
        }
        int cnt = 0;
        for(int i = 0; i < s.length(); i++){
            char currentChar = s.charAt(i);
            if(!set.contains(currentChar)) {
                tmp += currentChar;
            }
            else{
                cnt++;
            }
        }
        System.out.println(cnt);
        for(int i = 0; i < cnt; i++){
            result += order.charAt(i);
        }
        return result + tmp;
    }

    public static int maxFrequencyElements(int[] nums) {
        Map<Integer, Integer> frequency = new HashMap<>();
        for(int i: nums){
            frequency.put(i, frequency.getOrDefault(i, 0) + 1);
        }

        int maxFrequency = Integer.MIN_VALUE;
        for(Map.Entry<Integer, Integer> entry: frequency.entrySet()){
            if(maxFrequency < entry.getValue()){
                maxFrequency = entry.getValue();
            }
        }

        int result = 0;
        for(Map.Entry<Integer, Integer> entry: frequency.entrySet()){
            if(entry.getValue() == maxFrequency){
                result += maxFrequency;
            }
        }
        return result;
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int nums[] = new int[n];
        for(int i = 0; i < n; i++){
            nums[i] = sc.nextInt();
        }
        System.out.println(maxFrequencyElements(nums));

    }
}
