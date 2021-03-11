package com.example.javatest.listnode;

/**
 * Created by chengwen on 2021/3/7.
 * 回文字符串
 */
class Palindrome {

    /**
     * 给定字符串的某个字符开始寻找最大回文串:两个指针从中间分别向两边探索，左右两个参数是考虑str总长度为偶数情况
     *
     * @param str
     * @param left
     * @param right
     * @return
     */
    public String isPalindrome(String str, int left, int right) {
        if (left < right || right >= str.length() || left < 0) return null;
        while (left >= 0 && right < str.length() && (str.charAt(left) == str.charAt(right))) {
            left--;
            right++;
        }
        // str.substring, the beginning index, inclusive. the ending index, exclusive.
        return str.substring(left, right + 1);
    }

    /**
     * 寻找给定字符串的最长回文子串：从头遍历字符串，对经过的每个字符向两边探索回文串，考虑str总长度为偶数情况
     *
     * @param str
     * @return
     */
    public String longestPalindrome(String str) {
        if (str == null || str.isEmpty()) return null;
        String max = null;
        for (int i = 0; i < str.length(); i++) {
            String s1 = isPalindrome(str, i, i);
            String s2 = isPalindrome(str, i, i + 1);
            if (s1.length() >= s2.length()) {
                max = s1;
            } else {
                max = s2;
            }
        }
        return max;
    }

}
