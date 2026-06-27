class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> freq = new HashMap<>();

        for (int num : nums) {
            freq.put((long) num,
                    freq.getOrDefault((long) num, 0) + 1);
        }

        int ans = 1;

        // Special case for 1
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);

            if (cnt % 2 == 0) cnt--;

            ans = Math.max(ans, cnt);
        }

        for (long start : freq.keySet()) {

            if (start == 1L) continue;

            long curr = start;
            int len = 0;

            while (true) {

                int count = freq.getOrDefault(curr, 0);

                if (count >= 2) {
                    len += 2;

                    if (curr > 1_000_000_000L) break;

                    curr = curr * curr;
                }
                else if (count == 1) {
                    len += 1;
                    break;
                }
                else {
                    len -= 1;
                    break;
                }
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}