// Robbin Karp Algorithm Implementationi 

public class RobinKarpCustom{
    // Prime No. for modulus in hashing to minimize the collisions 
    private static final int PRIME = 101; 
    // Robbin Karp Algorithm to search for a pattern in a given text 
    public static int search(String text, String pattern){
        int m=pattern.length(); 
        int n=text.length(); 
        int patternHash=createHash(pattern, m); 
        int textHash=createHash(text, m); 
        // Sliding over the text 1char at a time 
        for(int i=0; i<=n-m; i++){
            if(patternHash == textHash && checkEqual(text, i, i+m-1, pattern, 0, m-1)){
                // if the hash value matches and strings are eqaul 
                return i; // Pattern found at ith index 
            }
            if(i<n-m){
                textHash=recalculateHash(text, i, i+m, textHash, m);
            }
        }
        return -1; //Pattern Not found 
    }
    // Meethod to calculate the intital HashValue 
    private static int createHash(String str, int end){
        int hash=0; 
        for(int i=0; i<end; i++){
            hash += str.charAt(i)*Math.pow(PRIME, i);
        }
        return hash;
    }
    // Method to recalculate the hash when sliding the window 
    private static int recalculateHash(String str, int oldIndex, int newIndex, int oldHash, int patternLen){
        int newHash=oldHash-str.charAt(oldIndex);
        newHash/=PRIME; 
        newHash+=str.charAt(newIndex)*Math.pow(PRIME, patternLen-1);
        return newHash;
    }
    // Method to check if two substrings are actually equall 
    private static boolean checkEqual(String text, int start1, int end1, String pattern, int start2, int end2){
        while(start1<=end1 && start2 <=end2){
            if(text.charAt(start1)!=pattern.charAt(start2)){
                return false;
            }
            start1++; 
            start2++;
        }
        return true;
    }
    // Main Method to check Robbin Karp Algortihm 
    public static void main(String[] args){
        String text="abcdefghi"; 
        String pattern="def"; 
        int result=search(text, pattern); 
        if(result!=-1){
            System.out.println("Pattern found at index: "+result);
        }else{
            System.out.println("Pattern Not found.");
        }
    }
}