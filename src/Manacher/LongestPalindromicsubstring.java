package Manacher;

import java.util.Arrays;

public class LongestPalindromicsubstring {
    public static void main(String[] args) {
        String text = "babcbabcbaccba";
        findLongestPalindromicString(text);

        text = "abaaba";
        findLongestPalindromicString(text);

        text = "abababa";
        findLongestPalindromicString(text);

        text = "abcbabcbabcba";
        findLongestPalindromicString(text);

        text = "forgeeksskeegfor";
        findLongestPalindromicString(text);

        text = "caba";
        findLongestPalindromicString(text);

        text = "abacdfgdcaba";
        findLongestPalindromicString(text);

        text = "abacdfgdcabba";
        findLongestPalindromicString(text);

        text = "abacdedcaba";
        findLongestPalindromicString(text);
    }

    private static void findLongestPalindromicString(String s) {
        StringBuilder builder = new StringBuilder();
        builder.append('#');
        for(char c : s.toCharArray()) {
            builder.append(c);
            builder.append('#');
        }
        s = builder.toString();
        int n = s.length();
        s = '$' + s + '^';
        int p[] = new int[n + 2];
        int l = 1, r = 1;
        for(int i = 1; i <= n; i ++) {
            p[i] = Math.max(0, Math.min(r - i, p[l + (r - i)]));
            while(s.charAt(i - p[i]) == s.charAt(i + p[i])) {
                p[i] ++;
            }

            if(i + p[i] > r) {
                l = i - p[i];
                r = i + p[i];
            }
        }
        Arrays.stream(p).forEach(System.out::print);
        System.out.println();
    }
}
