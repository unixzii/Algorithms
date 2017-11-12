package me.cyandev;

/**
 * Longest Common Subsequence solution using DP.
 *
 * State transition equation:
 *
 *             0                              (i = 0 or j = 0)
 * dp[i, j] =  dp[i - 1, j - 1] + 1           (i, j > 0 and s1[i] = s2[j])
 *             max{c[i - 1, j], c[i, j - 1]}  (i, j > 0 and s1[i] /= s2[j])
 */
public final class LCS {

    private static final int LEFT_TOP = 1;
    private static final int LEFT = 2;
    private static final int TOP = 3;

    public static String lcs(String s1, String s2) {
        final int len1 = s1.length() + 1;
        final int len2 = s2.length() + 1;

        int[][] dp = new int[len1][len2];
        int[][] flags = new int[len1][len2];

        for (int i = 1; i < len1; i++) {
            for (int j = 1; j < len2; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    flags[i][j] = LEFT_TOP;
                } else {
                    final int left = dp[i - 1][j];
                    final int top = dp[i][j - 1];
                    dp[i][j] = Math.max(left, top);
                    flags[i][j] = left > top ? LEFT : TOP;
                }
            }
        }

        final int lcsLength = dp[len1 - 1][len2 - 1];
        char[] lcs = new char[lcsLength];

        int p = lcsLength - 1;
        for (int i = len1 - 1; i > 0 && p >= 0;) {
            for (int j = len2 - 1; j > 0;) {
                switch (flags[i][j]) {
                    case LEFT_TOP:
                        i--;
                        j--;
                        if (p >= 0) {
                            lcs[p--] = s1.charAt(i);
                        }
                        break;
                    case LEFT:
                        i--;
                        break;
                    case TOP:
                        j--;
                }
            }
        }

        return new String(lcs);
    }

}
