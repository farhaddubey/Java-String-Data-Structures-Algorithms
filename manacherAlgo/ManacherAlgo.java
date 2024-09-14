import java.util.*; 

public class ManacherAlgo{
    public String longestPalindromeSubstring(String s){
        // Preprocessing: # Before After and Between every characters
        // aba--># a # b # a # 
        StringBuilder sb=new StringBuilder();
        sb.append("#"); 
        for(char c: s.toCharArray()){
            sb.append(c).append("#");
        }
        String processedString=sb.toString(); 

        // Declaring Variables 
        int n=processedString.length(); 
        int[] P=new int[n]; 
        int C=0, R=0; // To track the center and right boundary of the current pallindrome
        int maxLen=0; // To track the longest palindrome 
        int centerIndex=0; // To track the center of the longest palindrome 

        // To check or expand for all characters in the processed String 
        for(int i=0; i<n; i++){
            int mirror=2*C - i; 
            if(i<R){
                P[i]=Math.min(R-i, P[mirror]); // If within the range then trying to use the already got value
            }
            // Now expanding the palindrome around ith element ---> i 
            // 1 # 2 # 3 # 2 # 1
            while((i-P[i]-1)>=0 && (i+P[i]+1)<n && processedString.charAt(i-P[i]-1)==processedString.charAt(i+P[i]+1)){
                P[i]++; 
            }
            // Now checking if the index is within the R 
            if(i+P[i]>R){
                C=i; 
                R=i+P[i];
            }
            // Will be checking if the current value of P[i] is greater than the previously calculated 
            // longest palindrome  
            if(P[i]>maxLen){
                maxLen=P[i]; 
                centerIndex=i; 
            }
        }
        // Extracting the longest palindrome substrings 
        int start=(centerIndex-maxLen)/2; 
        return s.substring(start, start+maxLen);

    }
    public static void main(String[] args){
        String s="abbacdefghihgfedc";
        ManacherAlgo manacher=new ManacherAlgo(); 
        System.out.println("The longest palindrome is: "+manacher.longestPalindromeSubstring(s));

    }
}