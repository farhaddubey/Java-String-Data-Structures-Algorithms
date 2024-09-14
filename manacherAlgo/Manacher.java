import java.util.*; 

public class Manacher{
    public static void main(String[] args){
        // Step1. Preprocessing the string 
        StringBuilder sb=new StringBuilder(); 
        sb.append('#'); 
        for(char c: s.toCharArray()){
            sb.append(c).append('#'); 
        }
        String processedString = sb.toString(); 

        int n=processedString.length(); 
        int[] P=new int[n]; // Array to store the palindrome length 
        int C=0, R=0; // Current center & right boundary 
        int maxLen = 0; // To track the maximum pallindrome length 
        int centerIndex =0; // Center index of the maximum pallindrome 

        // Step 2: Expanding around the center 
        for(int i=0; i<n; i++){
            int mirror=2*C - i; // Mirrored posiotion of i with respect to C 
            if(i<R){
                P[i]=Math.min(R-i, P[mirror]);
            }
            // Trying to expand pallindrome centered at i 
            while(i+P[i]+1<n && i-P[i]-1>=0 && processedString.charAt(i+ P[i]+1) == processedString.charAt(i-P[i]-1)){
                P[i]++;
            }
            // If pallindrome centered at i expands beyond R, adjust C and R 
            if(i + P[i]>R){
                C=i; 
                R=i + P[i];
            }
            // Tracking the maximum palindrome length 
            if(P[i] > maxLen){
                maxLen=P[i]; 
                centerIndex = i; 
            }
        }
    // Step 3: Extracting the longest pallindrome Substring 
    int start =(centerIndex - maxLen)/2;  // Converting back to the original String index 
    return s.substring(start, start + maxLen); 
    }
    public static void main(String[] args){
        String s="abbacdefghihgfe"; 
        System.out.println("Longest pallindrome Substring: "+longestPalindromeSubstring(s));
    }
}