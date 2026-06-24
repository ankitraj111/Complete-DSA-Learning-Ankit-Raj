class Solution {
    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1) return m;

        int size = 2 * m;
        long[][] T = new long[size][size];

        // up[v] = prefix of down below v
        for (int v = 0; v < m; v++) {
            for (int u = 0; u < v; u++) {
                T[v][m + u] = 1;
            }
        }

        // down[v] = suffix of up above v
        for (int v = 0; v < m; v++) {
            for (int u = v + 1; u < m; u++) {
                T[m + v][u] = 1;
            }
        }

        long[] state = new long[size];

        // length 2 initialization
        for (int v = 0; v < m; v++) {
            state[v] = v;              // up
            state[m + v] = m - 1 - v; // down
        }

        long[][] P = power(T, n - 2);

        long[] ansState = multiply(P, state);

        long ans = 0;
        for (long x : ansState)
            ans = (ans + x) % MOD;

        return (int) ans;
    }

    private long[][] power(long[][] A, int exp) {
        int n = A.length;
        long[][] res = new long[n][n];

        for (int i = 0; i < n; i++)
            res[i][i] = 1;

        while (exp > 0) {
            if ((exp & 1) == 1)
                res = multiply(res, A);

            A = multiply(A, A);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                for (int j = 0; j < n; j++) {
                    C[i][j] = (C[i][j] + A[i][k] * B[k][j]) % MOD;
                }
            }
        }
        return C;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i] = (res[i] + A[i][j] * v[j]) % MOD;
            }
        }
        return res;
    }
}