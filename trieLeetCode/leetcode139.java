class Solution {
    // TrieNode Class 
    class TrieNode {
        Map<Character, TrieNode> children=new HashMap<>(); 
        boolean isWord=false; 
    }
    TrieNode root; 
    // Constructor to initialize the trie 
    public Solution(){
        root=new TrieNode();
    }
    // Inserting words from wordDict into the Trie 
    public void insert(String word){
        TrieNode node=root; 
        for(char c:word.toCharArray()){
            if(!node.children.containsKey(c)){
                node.children.put(c, new TrieNode()); 
            }
            node = node.children.get(c); 
        }
        node.isWord=true; // Marking thee end of a valiid word
    }
    // // Utilizing Trie & DP in the Word Break problem 
    // public boolean wordBreak(String s, List<String> wordDict) {
    //     // Inserting all words from wordDict into the Trie 
    //     for(String word: wordDict){
    //         insert(word);
    //     }
    //     // DP array to track whether s[0:i] can be segmented 
    //     boolean[] dp=new boolean[s.length() +1]; 
    //     dp[0]=true; // An empty string is alwasy valid 
    //     // Traversing the string 
    //     for(int i=0; i<s.length(); i++){
    //         if(dp[i]){
    //             TrieNode node=root; 
    //             for(int j=i; j<s.length(); j++){
    //                 char c=s.charAt(j);
    //                 if(!node.children.containsKey(c)){
    //                     break;
    //                 }
    //                 node =node.children.get(c);
    //                 if(node.isWord){
    //                     dp[j+1]=true; 
    //                 }
    //             }
    //         }
    //     }
    //     return dp[s.length()];
        
    // }
    // Optimized WordBreak function with memoization 
    public boolean wordBreak(String s, List<String> wordDict){
        // Inserting all  words into the Trie 
        for(String word: wordDict){
            insert(word);
        }
        // Using a memoization map to cache results 
        Map<Integer, Boolean> memo=new HashMap<>(); 
        // Calling the recursive helper function 
        return wordBreakHelper(s, 0, memo);
    }
    // Helper functioon with Trie traversal and memoization 
    public boolean wordBreakHelper(String s, int start, Map<Integer, Boolean> memo){
        // If we've reached the end of the string, returning true 
        if(start==s.length()){
            return true; 
        }
        // If this start index is already computed, returning the cache result 
        if(memo.containsKey(start)){
            return memo.get(start);
        }
        TrieNode node =root; 
        // Trying to match substrings starting from 'start' 
        for(int i=start; i<s.length(); i++){
            char c=s.charAt(i); 
            // If the chacracter is not in the Trie, no point continueing 
            if(!node.children.containsKey(c)){
                break; 
            }
            node=node.children.get(c); 
            // If we found a valid word in the trie, recurese for the remaining substring 
            if(node.isWord){
                if(wordBreakHelper(s, i+1, memo)){
                    memo.put(start, true); // Caching the result 
                    return true; 
                }
            }
        }
        // Caching the reuslt fot the start index 
        memo.put(start, false); 
        return false;
    }
    // Main method to test the code 
    public static void main(String[] args){
        Solution sol1=new Solution();
        String s1="leetcode"; 
        List<String> wordDict1=Arrays.asList("leet", "code");
        String s2="applepenapple";
        List<String> wordDict2=Arrays.asList("apple", "pen");
        String s3="catsanddog"; 
        List<String> wordDict3=Arrays.asList("cats", "dog", "sand", "and", "cat");
        
    }
}