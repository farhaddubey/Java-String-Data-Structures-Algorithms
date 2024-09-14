class Solution {
    public int countSubstrings(String s) {
        //Step 1. Processing the String 
        StringBuilder sb=new StringBuilder(); 
        sb.append("#"); 
        for(char c:s.toCharArray()){
            sb.append(c).append("#");
        }
        String processedString = sb.toString(); 

        //Step 2. Allocating ie. Declaring the variables 
        int n=processedString.length(); 
        int[] P=new int[n]; 
        int C=0, R=0; 
        int count=0;

        //Step 3. Expanding around the center 
        for(int i=0; i<n; i++){
            int mirror=2*C-i; // Mirrored positioin of i with respect to C, to ignore few comparisons 
            if(i<R){
                P[i]=Math.min(R-i, P[mirror]); 
            }
            // Trying to expand palindrome centered at i 
            while(i+P[i]+1<n && i-P[i]-1>=0 && processedString.charAt(i+P[i]+1)==processedString.charAt(i-P[i]-1)){
            
                P[i]++;
            }
            // If palindrome centered at i expands beyonds R, adjust C, R 
            if(i+P[i]>R){
                C=i; 
                R=i+P[i];
            }
            // Counting pallindromes to excluding the # character 
            count +=(P[i]+1)/2;
            // P[i] is 0 where for i no palindrome found & the formual of addding 1 & dividning by 2 always provides the count exlcluding the # character . #a#b#a#->3 but actaual aba is 2
            

        }
            return count;
    }
    public static void main(String[] args){
        Solution sol1=new Solution(); 
        String s="abc";
        int result=sol1.countSubstrings(s);
    }
}
















// Let's break down the line count += (P[i] + 1) / 2; in the context of Manacher's Algorithm and the processed string with # characters inserted between the actual characters:

// Key Points:
// Processed String:

// In Manacher's Algorithm, we preprocess the string by inserting a # between every character and at the ends of the string. This helps to handle both odd and even length palindromes uniformly.
// For example, the string "abc" becomes "#a#b#c#".
// P Array:

// P[i] is the radius of the longest palindrome centered at the i-th character in the processed string.
// Since we inserted # between characters, P[i] represents the radius of the palindrome including those # characters.
// Counting Palindromes:

// For each P[i], if it is greater than 0, it means there's a palindrome centered at i with a radius of P[i] (in the processed string).
// Palindromes of even length in the original string (like "aa") will be centered at positions with #, while odd-length palindromes (like "aba") will be centered at positions corresponding to characters of the original string.
// Why (P[i] + 1) / 2:

// P[i] gives the total radius in the processed string, but this includes # characters.

// For example:

// If P[i] = 3, it means there's a palindrome of length 3 in the processed string (like "#a#b#a#"), which corresponds to a palindrome of length 2 in the original string ("aba").
// If P[i] = 1, it means there's a palindrome of length 1 in the processed string (like "#a#"), corresponding to a single character palindrome ("a").
// So, to count the actual palindromes (excluding # characters), we compute the number of valid substrings as:

// (P[i] + 1) / 2:
// P[i] is the total length in the processed string.
// Adding 1 ensures that we are counting valid palindromes.
// Dividing by 2 gives the number of palindromes in the original string.
// Example:
// Consider P[i] = 3. This means there’s a palindrome in the processed string with a radius of 3. The corresponding palindromes in the original string would be "a", "b", and "a". So (P[i] + 1) / 2 = (3 + 1) / 2 = 2, meaning there are 2 palindromic substrings.
// Similarly, for P[i] = 1, (P[i] + 1) / 2 = (1 + 1) / 2 = 1, meaning there is 1 palindromic substring.
// Summary:
// The formula (P[i] + 1) / 2 converts the radius in the processed string (which includes # characters) into the actual number of palindromic substrings in the original string.