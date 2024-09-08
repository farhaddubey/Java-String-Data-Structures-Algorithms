// 28. Find the Index of the First Occurrence in a String---------KMP


class Solution {
    // Prime no. for modulus in hashing to minimize the collisions 
    private static final int PRIME=101; 
    public int strStr(String haystack, String needle) {
        int n=haystack.length(); 
        int m=needle.length(); 

        //Edge case: if the needle is Empty, return 0; 
        if(m==0) return 0; 
        //Edge case: if the haystack is shorter than the needle, return -1; 
        if(n<m) return -1; 
        //Calculating the hash of the needle and the first substring of the haystack
        int patternHash=createHash(needle, m);
        int textHash=createHash(haystack, m); 
        // Sliding over the text one character at a time 
        for(int i=0; i<=n-m; i++){
            if(patternHash==textHash && checkEqual(haystack, i, i+m-1, needle, 0, m-1)){
                // If the hash value mathches, and the strings are equal 
                return i; // Pattern found at index i;

            }
            // Recalculating hash for the next substring 
            if(i<n-m){
                textHash=recalculateHash(haystack, i, i+m, textHash, m);
            }
        }
        return -1; //Pattern not found.
        
    }
    //Method for creating the initial hash value while applying Rabin-Karp algorithm
    //for the 1st m characters  
    private static int createHash(String str, int length){
        int hash=0; 
        for(int i=0; i<length; i++){
            hash+=str.charAt(i)*Math.pow(PRIME, i);
        }
        return hash;
    }
    //Method to recalculate the hash when sliding the window 
    private static int recalculateHash(String str, int oldIndex, int newIndex, int oldHash, int patternLen){
        int newHash=oldHash-str.charAt(oldIndex); 
        newHash/=PRIME; 
        newHash+=str.charAt(newIndex)*Math.pow(PRIME, patternLen-1);
        return newHash;
    }
    //Method to check if the two substrings are actually equal 
    private static boolean checkEqual(String text, int start1, int end1, String pattern, int start2, int end2){
        while(start1<=end1 && start2<=end2){
            if(text.charAt(start1)!=pattern.charAt(start2)){
                return false; 
            }
            start1++; 
            start2++; 
        }
        return true;
    }
    public static void main(String[] args){
        Solution sol1 = new Solution();
        String haystack="leetcode";
        String needle="leeto";
        int result=sol1.strStr(haystack, needle);
    }
}