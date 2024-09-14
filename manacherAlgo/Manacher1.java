public class Manacher1{
    public static String longestPalindromeSubstring(String s){
        // Step1. Processing the String 
        StringBuilder sb=new StringBuilder(); 
        sb.append('#'); 
        for(char c: s.toCharArray()){
            sb.append(c).append('#');
        }
        String processedString = sb.toString(); 

        // Step2. Allocating ie. Declaring the Variables 
        int n=processedString.length(); 
        int[] P=new int[n]; // This array is to store the palindrome lengths around that index character 
        int C=0, R=0; // Current center and Right Boundary 
        int maxLen=0; // fOR TRACKING the maximum length 
        int centerIndex=0; // Center Index of the maximum palindrome. 

        // Step3. Expanding around the Center 
        for(int i=0; i<n; i++){
            int mirror=2*C-i; // Mirroreed position of i with respect to C 

            if(i<R){
                P[i] = Math.min(R-i, P[mirror]);
            }

            // Trying to expand palindrome centered at i 
            while(i+P[i]+1<n && i-P[i]-1>=0 && processedString.charAt(i+P[i]+1)==processedString.charAt(i-P[i]-1)){
                P[i]++; 
            }

            // If palindrome centered at i expandes beyond R, adjust C and R 
            if(i+P[i]> R){
                C=i; 
                R=i+P[i]; 
            }

            // If the current length of P[i] is greate than maxLen then updating the center & maxLen
            if(P[i]>maxLen){
                maxLen=P[i];
                centerIndex=i;
            }
        }


        // Step 3. Extracting the longest palindromic substring
        int start=(centerIndex-maxLen)/2; // Assigning the starting index 
        return s.substring(start, start+maxLen);
    }
    public static void main(String[] args){
        String s="abbabcdcbaffghihgffcd"; 
        System.out.println("Longest Pallindromic Substring: "+longestPalindromeSubstring(s));
    }
}