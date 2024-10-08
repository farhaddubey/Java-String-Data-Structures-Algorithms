import java.util.*; 

public class OptimizedRabinKarp{
    private static final int d=256; // ASCII charset 
    // A prime no. for the minimum in hashing to minimize collisions 
    private static final int PRIME =101; 

    // Optimized Rabin-Karp Algorithm to search for a pattern in a given text 
    public static int search(String text, String pattern){
        int m=pattern.length(); 
        int n=text.length(); 

        // Calculating the initial hash values and hash multiplier 
        HashData hashData = initializeHash(text, pattern, m); 

        // Sliding the pattern over the text 
        for(int i=0; i<=n-m; i++){
            if(hashData.patternHash == hashData.textHash && checkEqual(text, i, i+m-1, pattern, 0, m-1)){
                return i; // Pattern found at index i
            }
            // Recalculating the hash for the next window 
            if(i<n-m){
                hashData.textHash = recalculateHash(text, hashData.textHash, i, i+m, hashData.h, m);
            }
        }
        return -1; // Pattern Not found 
    }

    // Function to initialize hash values for both patterns and the first text window
    private static HashData initializeHash(String text, String pattern, int m){
        int patternHash=0, textHash=0, h=1; 
        // Calculate hash h=(d^(m-1))%PRIME, used for shifting the window 
        for(int i=0; i<m-1; i++){
            h=(h*d)%PRIME; 
        }
        // Calculating the initial hashvalues for pattern and text 
        for(int i=0; i<m; i++){
            patternHash=(d*patternHash+pattern.charAt(i))%PRIME; 
            textHash=(d*textHash+text.charAt(i))%PRIME; 
        }
        return new HashData(patternHash, textHash, h);
    }

    // Function to recalculate the hash for the next window in the text 
    private static int recalculateHash(String text, int textHash, int oldIndex, int newIndex, int h, int m){
        textHash =(d*(textHash-text.charAt(oldIndex)*h)+text.charAt(newIndex))%PRIME; 
        // Handling -ve hash values 
        if(textHash<0){
            textHash+=PRIME; 
        }
        return textHash; 
    }

    // Method to check if two substrings are actually equal 
    private static boolean checkEqual(String text, int start1, int end1, String pattern, int start2, int end2){
        while(start1<=end1 && start2<=end2){
            if(text.charAt(start1) != pattern.charAt(start2)){
                return false;
            }
            start1++; 
            start2++; 
        }
        return true; 
    }

    // A simple data structure to hold hash related data 
    private static class HashData{
        int patternHash; 
        int textHash; 
        int h; 
        HashData(int patternHash, int textHash, int h){
            this.patternHash=patternHash; 
            this.textHash=textHash; 
            this.h=h; 
        }
    }

    // Main method to test the robin karp algorithm 
    public static void main(String[] args){
        String text="abcdefghi"; 
        String pattern="def";
        int result=search(text, pattern); 
        if(result!=-1){
            System.out.println("Pattern found at index: "+ result); 
        }else{
            System.out.println("Pattern Not found.");
        }
    }
}