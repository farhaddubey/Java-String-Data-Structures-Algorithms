import java.util.HashMap; 
import java.util.Map; 

public class BoyerMoore2 {

    //Preprocessing the pattern to create the bad character heuristic array 
    private int[] preprocessBadCharacter(String pattern){
        int[] badChar=new int[256]; // Size of the ASCII Charset 
        // Initializing all positions to -1 
        for(int i=0; i<256; i++){
            badChar[i]=-1; 
        }
        // Filling the badChar array with the last occurrence index of each charcater in the pattern. 
        for(int i=0; i<pattern.length(); i++){
            badChar[pattern.charAt(i)]=i; // With i incrementing automatically the highest index or last occu..
            // occurrence will be reassigned 
        }
        return badChar;
    }

    // Preprocessing the pattern to create the good suffix heuristic array 
    private int[] preprocessGoodSuffix(String pattern){
        int m=pattern.length(); // Length of the pattern 
        int[] goodSuffix=new int[m]; // Array to store the good suffix shift distances 
        int[] borderPos=new int[m+1]; // Array to store border positions 

        int i=m; // Starting from the end of the pattern 
        int j=m+1; // Border position of the last occurrence is outside the pattern 
        borderPos[i]=j; // Border position of the last character is outside the pattern 

        // Process the pattern from end to start to fill borderPos and goodSuffix 
        while(i>0){
            while(j<=m && pattern.charAt(i-1) != pattern.charAt(j-1)){
                if(goodSuffix[j]==0){
                    goodSuffix[j] = j-i; // Shift pattern by the distance between j and i 
                }
                j= borderPos[j]; // Updating j to the border position 
            }
            i--; 
            j--; 
            borderPos[i]=j; // Update the border position for current i
        }
        // Filling the  goodSuffix array based on the border positions 
        for(i=0; i<=m; i++){
            if(goodSuffix[i]==0){
                goodSuffix[i]=j; 
            }
            if(i==j){
                j=borderPos[j]; 
            }
        }
        return goodSuffix; 
    }

    // the main function to search fot the pattern in the text using Boyer Moore algorithm 
    public int search(String text, String pattern){
        int m=pattern.length(); 
        int n=text.length(); 
        // Preprocessing the pattern to get the good char and bad suffix array 
        int[] badChar=preprocessBadCharacter(pattern); 
        int[] goodSuffix=preprocessGoodSuffix(pattern); 
        int shift=0; // The position shift of the pattern with respect to the text 
        // Looping until the pattern can no longer in the remaining text 
        while(shift<=(n-m)){
            int j=m-1; // Starting comparison from the end ie. the last index of the pattern 
            // Decreasing j while the characters of the pattern and text are matching 
            while(j>=0 && pattern.charAt(j)==text.charAt(j+shift)){
                j--;
            }
            // if the entire pattern was mateched, then after the last iteration j will be -1 from index 0
            if(j<0){
                System.out.println("Pattern found at index: " + shift);// Pattern found at the current shift; 
                // Now shifting the pattern according to the good suffix heuristics 
                shift +=(shift+m<n)?m-badChar[text.charAt(shift+m)]: 1;
            }else{
                // Shifting the pattern according to the maximum of badChar & goodSuffix heruristics 
                shift +=Math.max(1, Math.max(j-badChar[text.charAt(shift+j)], goodSuffix[j+1]));
            }
        }
        return -1; // Pattern not found. 
    }
    public static void main(String[] args){
        BoyerMoore2 bm=new BoyerMoore2(); 
        String text="abacaabadcabacabaabb"; 
        String pattern="abacab"; 
        int result=bm.search(text, pattern); 
        System.out.println("Pattern found at: "+ result);

    }

}