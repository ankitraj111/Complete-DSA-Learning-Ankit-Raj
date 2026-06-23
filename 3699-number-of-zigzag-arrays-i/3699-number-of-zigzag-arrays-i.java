class Solution {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        long[] up = new long[m + 1];
        long[] down = new long[m + 1];

        // Length = 2
        for (int v = 1; v <= m; v++) {
            up[v] = v - 1;      // smaller values
            down[v] = m - v;    // larger values
        }

        // Build lengths 3..n
        for (int len = 3; len <= n; len++) {

            long[] prefixDown = new long[m + 1];
            long[] suffixUp = new long[m + 2];

            for (int v = 1; v <= m; v++) {
                prefixDown[v] = (prefixDown[v - 1] + down[v]) % MOD;
            }

            for (int v = m; v >= 1; v--) {
                suffixUp[v] = (suffixUp[v + 1] + up[v]) % MOD;
            }

            long[] newUp = new long[m + 1];
            long[] newDown = new long[m + 1];

            for (int v = 1; v <= m; v++) {
                // previous value < v and previous move was down
                newUp[v] = prefixDown[v - 1];

                // previous value > v and previous move was up
                newDown[v] = suffixUp[v + 1];
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;

        if (n == 2) {
            for (int v = 1; v <= m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
        } else {
            for (int v = 1; v <= m; v++) {
                ans = (ans + up[v] + down[v]) % MOD;
            }
        }

        return (int) ans;
    }
}