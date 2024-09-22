import java.util.*;

public class RobinKarpOptimized{
    // ASCII Character set 
    private static final int d=256; 
    // A prime no. for the modulus in hashing to minimize collisions 
    private static final int PRIME = 101; 
    // Optimized Robin-Karp Algorithm to search for a pattern in a given text 
    public static int search(String text, String pattern){
        int m=pattern.length(); 
        int n=text.length(); 
        int patternHash = 0; // Hash Value for the pattern 
        int textHash = 0; // Hash Value for the text window
        int h=1; // used to calculate the hash for the next window 

        // Precomputing hash h=(d^(m-1))%PRIME 
        for(int i=0; i<m-1; i++){
            h=(h*d)%PRIME; 
        }

        // Calculating initial hash for the pattern and the first window of the text 
        for(int i=0; i<m; i++){
            patternHash = (d*patternHash + pattern.charAt(i)) % PRIME; 
            textHash = (d*textHash + text.charAt(i)) % PRIME; 
        }

        // Sliding the pattern over the text one character at a time 
        for(int i=0; i<=n-m; i++){
            // If the hash values match, checking if the actual strings match
            if(patternHash==textHash && checkEqual(text, i, i+m-1, pattern, 0, m-1)){
                return i; // Pattern found at index i 
            }
            // Calculating the hash for the next window of the text 
            if(i<n-m){
                textHash = (d*(textHash-text.charAt(i)*h)+text.charAt(i+m))%PRIME; 
                // Handling -ve hash value by converting it to positive 
                if(textHash<0){
                    textHash=(textHash+PRIME);
                }
            }
        }
        return -1; // Pattern not found 
    }
    
    // Method to check if two strings are actually equal 
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

    // Main Method to test the optimized Robin-karp algorithm 
    public static void main(String[] args){
        String text="abcdefghi"; 
        String pattern="def";
        int result = search(text, pattern); 
        if(result != -1){
            System.out.println("Pattern found at index: "+result);
        }else{
            System.out.println("Pattern not found."); 
        }
    }
}













