import java.util.*;

class Solution {
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isWord = false;
    }

    TrieNode root;

    // Constructor to initialize the Trie
    public Solution() {
        root = new TrieNode();
    }

    // Insert a word into the Trie
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c);
        }
        node.isWord = true; // Mark the end of a word
    }

    // Function to find all possible sentences
    public List<String> wordBreak(String s, List<String> wordDict) {
        // Insert all words from wordDict into the Trie
        for (String word : wordDict) {
            insert(word);
        }

        // Use a memoization map to store results for substrings
        Map<Integer, List<String>> memo = new HashMap<>();
        return backtrack(s, 0, memo);
    }

    // Backtracking function to find all valid sentences starting from index 'start'
    private List<String> backtrack(String s, int start, Map<Integer, List<String>> memo) {
        // If we have already computed the result for this substring, return it
        if (memo.containsKey(start)) {
            return memo.get(start);
        }

        List<String> results = new ArrayList<>();
        TrieNode node = root;

        // If we reach the end of the string, add an empty string (base case)
        if (start == s.length()) {
            results.add("");
            return results;
        }

        // Traverse the string from 'start' and try to find valid words in the Trie
        for (int i = start; i < s.length(); i++) {
            char c = s.charAt(i);

            // If the character does not exist in the Trie, break the loop
            if (!node.children.containsKey(c)) {
                break;
            }

            node = node.children.get(c);

            // If we find a valid word, recursively try to find the rest of the sentence
            if (node.isWord) {
                List<String> subSentences = backtrack(s, i + 1, memo);
                for (String sub : subSentences) {
                    // Append the current word to each valid sub-sentence
                    results.add(s.substring(start, i + 1) + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
        }

        // Store the result for the current start index in memo
        memo.put(start, results);
        return results;
    }

    // Main method to test the solution
    public static void main(String[] args) {
        Solution solution = new Solution();

        String s1 = "catsanddog";
        List<String> wordDict1 = Arrays.asList("cat", "cats", "and", "sand", "dog");
        System.out.println(solution.wordBreak(s1, wordDict1));
        // Output: ["cats and dog", "cat sand dog"]

        String s2 = "pineapplepenapple";
        List<String> wordDict2 = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(solution.wordBreak(s2, wordDict2));
        // Output: ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"]

        String s3 = "catsandog";
        List<String> wordDict3 = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(solution.wordBreak(s3, wordDict3));
        // Output: []
    }
}
