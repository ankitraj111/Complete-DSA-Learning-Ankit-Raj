class Solution {
    public int maxNumberOfBalloons(String text) {
        int[] freq = new int[26];

        // Count frequency of each character
        for (char ch : text.toCharArray()) {
            freq[ch - 'a']++;
        }

        // Calculate the maximum number of "balloon"s
        return Math.min(
                Math.min(freq['b' - 'a'], freq['a' - 'a']),
                Math.min(freq['l' - 'a'] / 2,
                         Math.min(freq['o' - 'a'] / 2, freq['n' - 'a']))
        );
    }
}