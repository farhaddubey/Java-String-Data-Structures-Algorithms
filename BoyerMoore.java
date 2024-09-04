import java.util.*;

public class BoyerMoore{
    private final int[] badCharTable; 
    private final int[] goodSuffixTable; 
    private final String pattern; 

    public BoyerMoore(String pattern){
        this.pattern=pattern; 
        badCharTable=buildBadCharTable(pattern);
        goodSuffixTable=buildGoodSuffixTable(pattern);
    }
    public int search(String text){
        int n=text.length(); 
        int m=pattern.length(); 
        int s=0; // s is the shift of the pattern with respect to the text 

        while(s<=(n-m)){
            int j=m-1; 
            // Decrease index j of the pattern, while characters of pattern and the text are matching 
            // j denoting rightnow the last character of the pattern 
            while(j>=0 && pattern.charAt(j)==text.charAt(s+j)){
                j--;
            }
            // If pattern is present at the current shift, then index j will become -1, as at j=0 it enters 
            // and becomes -1 
            if(j<0){
                System.out.println("Pattern occurs at shift: "+s); 
                // Shift the pattern so that the bad character in text aligns with the last occurrence or rightmost
                // occurrence of it in the 
                // pattern  
                s+=Math.max(1, j-badCharTable[text.charAt(s+j)]);

            }
        }
        return -1; //Pattern is not found 
    }
    private int[] buildBadCharTable(String pattern){
        int[] table=new int[256]; // Assuming extended ASCII 
        for(int i=0; i<table.length; i++){
            table[i]=-1; // Initialize all occurrence as -1 
        }
        for(int i=0; i<pattern.length(); i++){
            table[pattern.charAt(i)]=i;  // Setting the last occurrence of a character in the pattern.
        }
        return table; 
    }
    private int[] buildGoodSuffixTable(String pattern){
        int m=pattern.length(); 
        int[] table = new int[m]; 
        int lastPrefixPosition = m; 

        for(int i=m-1; i>=0; i++){
            if(isPrefix(pattern, i+1)){
                lastPrefixPosition=i+1; 
            }
            table[m-1-i] = lastPrefixPosition-i+m-1; 
        }

        for(int i=0; i<m-1; i++){
            int slen=suffixLength(pattern, i); 
            table[slen]=m-1-i+slen;
        }
        return table;
    }
    private boolean isPrefix(String pattern, int p){
        int j=0; 
        for(int i=p; i<pattern.length(); i++){
            if(pattern.charAt(i)!=pattern.charAt(j)){
                return false; 
            }
            j++;
        }
        return true;
    }
    private int suffixLength(String pattern, int p){
        int len=0; 
        int j=pattern.length() -1; 
        while(p>=0 && pattern.charAt(p)==pattern.charAt(j)){
            len++;  
            p--; 
            j--;
        }
        return len; 
    }
    public static void main(String[] args){
        BoyerMoore bm=new BoyerMoore("ABCD");
        bm.search("ABC ABCDAB ABCDABCDABDE"); 
    }
}