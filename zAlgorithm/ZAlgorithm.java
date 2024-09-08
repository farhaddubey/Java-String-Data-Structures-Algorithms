// ZAlgorithm Implementation in Java  

public class ZAlgorithm{
    // Function to calculate the Z-array 
    public static int[] calculateZ(String s){
        int n=s.length(); 
        int[] Z=new int[n]; 
        Z[0] =n; // Z[0] is always the length of the Entire string 
        int l=0, r=0; // Initialzing the bounds of the current Z-box 
        // Looping through the string to compute the Z-values 
        for(int i=1; i<n; i++){
            if(i<=r){
                // Inside the z-box use the previously computed z-valye to optimize 
                Z[i]=Math.min(r-i+1, Z[i-1]); 
            }
            // Trying to extend the match beyond the current Z-box 
            while(i+Z[i] <n && s.charAt(Z[i])==s.charAt(i+Z[i])){
                Z[i]++; 
            }
            // Updating the Z-box if the current match is longer 
            if(i+Z[i]-1>r){
                l=i; 
                r=i+Z[i]-1;
            }
        }
        return Z; 
    }             
    // Function to search for a pattern in the given text using the Z-algorithm 
    public static void search pattern(String text, String pattern){
        // Concatenating pattern + "$" + text 
        String concat = pattern + "$" + text; 
        int[] Z=calculateZ(concat);
        // The pattern length 
        int patternLength = pattern.length(); 
        // Iterating through the Z-array to find matches 
        for(int i=0; i<Z.length; i++){
            // If Z-value equals the length of the pattern, it's a match 
            if(Z[i]==patternLength){
                // The pattern occurs at position i - patternlength - 1 in the text 
                System.out.println("Pattern found at index: "+(i-patternLength-1));
            }
        }
    }                                                   
    public static void main(String[] args){
        String text = "ababcabcabababd"; 
        String pattern="ababd";
        System.out.println("Searching for pattern: "+pattern);
        searchPattern(text, pattern);
    } 
}