import java.util.*; 

public class leetcode647n2{
    public int countSubstrings(String s){
        int count=0; 
        // Expanding around each character 
        for(int i=0; i<s.length(); i++){
            count += expandAroundCenter(s, i, i); // For Odd-length pallindromes as it'll expand self in edd length manner 
            count += expandAroundCenter(s, i, i+1); // it'll be expandin self only in even lengths manner 

        }
        return count;
    }
    // Method to expand around center and count pallindromes 
    private int expandAroundCenter(String s, int left, int right){
        int count=0; 
        while(left>=0 && right<s.length() && s.charAt(left)==s.charAt(right)){
            count++; 
            left--; 
            right++; 
        }
        return count; 
    }
    public static void main(String[] args){
        leetcode647n2 sol=new leetcode647n2(); 
        String s1="abc"; 
        String s2="aaa"; 
        System.out.println("Count for abc: "+sol.countSubstrings(s1)); 
        System.out.println("Count for aaa: "+sol.countSubstrings(s2));
    }
}