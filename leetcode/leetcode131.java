import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), s, 0);
        return result;
    }

    // Backtracking function to generate all possible partitions
    private void backtrack(List<List<String>> result, List<String> tempList, String s, int start) {
        // If we've reached the end of the string, add the current partition to the result
        if (start == s.length()) {
            result.add(new ArrayList<>(tempList));
            return;
        }

        // Try to partition the string at different points
        for (int i = start; i < s.length(); i++) {
            // Check if the current substring is a palindrome
            if (isPalindrome(s, start, i)) {
                // If it is a palindrome, include it in the current partition
                tempList.add(s.substring(start, i + 1));
                // Recur for the remaining string
                backtrack(result, tempList, s, i + 1);
                // Backtrack by removing the last added partition
                tempList.remove(tempList.size() - 1);
            }
        }
    }

    // Helper function to check if a substring is a palindrome
    private boolean isPalindrome(String s, int low, int high) {
        while (low < high) {
            if (s.charAt(low++) != s.charAt(high--)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        String s = "aab";
        List<List<String>> partitions = sol.partition(s);

        // Output the partitions
        for (List<String> partition : partitions) {
            System.out.println(partition);
        }
    }
}







// Backtracking:

// The backtrack function takes four arguments:
// result: A list of lists that stores all valid partitions.
// tempList: A temporary list to store the current partition.
// s: The original string.
// start: The current starting index to partition the string.
// For every substring starting at start and ending at index i, we check if the substring is a palindrome using the isPalindrome function. If it is, we add it to tempList and recursively call backtrack for the next part of the string.
// Palindrome Check:

// The isPalindrome function checks if a substring (from index low to high) is a palindrome by comparing the characters from both ends.
// Base Case:

// If the start index reaches the end of the string, it means we've found a valid partition. We add the tempList to the result.
// Example Walkthrough:
// Input: "aab"
// Output: [["a","a","b"],["aa","b"]]
// The algorithm explores the following partitions:

// ["a", "a", "b"]: Every substring is a palindrome.
// ["aa", "b"]: "aa" is a palindrome, and "b" is a palindrome.
// Time Complexity:
// Time Complexity: O(n * 2^n) where n is the length of the string. The 2^n comes from the fact that we explore all possible ways to partition the string (each character can either be the start of a new partition or part of the current one). The n comes from the time needed to check if each substring is a palindrome.
// Space Complexity: O(n) for the recursive call stack and space for storing temporary lists.