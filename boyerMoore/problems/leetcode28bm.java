class Solution {
    public int strStr(String haystack, String needle) {
        int m=needle.length(); 
        int n=haystack.length(); 

        if (m==0) return 0; // if the needle is empty return 0;

        int[] badChar = preprocessBadCharacter(needle); 
        int[] goodSuffix = preprocessGoodSuffix(needle); 
        
        int shift=0; //The shift of the pattern with respect to the text 
        while(shift<=(n-m)){ //Looping until the pattern can be fully compared with the text 
            int j=m-1; 
            // Starting comparison form the end of the pattern ie. last char of the pattern 
            while(j>=0 && needle.charAt(j)==haystack.charAt(j+shift)){
                j--;
            }
            // if the pattern is present at the current shift, return the position
            if(j<0){
                return shift; // 1st occurrence found, return the index.    
            }else{
                // Using the maximum shift given by the bad char or good suffix heuristics
                shift+=Math.max(1, Math.max(j-badChar[haystack.charAt(shift+j)], goodSuffix[j]));
            }
        }
        return -1; // Pattern not found 
        
    }
    // Preprocessing for the bad character heuristics 
    private int[] preprocessBadCharacter(String pattern){
        int[] badChar = new int[256]; // size of the ASCII charset 
        for(int i=0; i<256; i++){
            badChar[i]=-1; // Initialize all to -1 
        }
        for(int i=0; i<pattern.length(); i++){
            badChar[pattern.charAt(i)] = i; // Fill the last occurrence index 
        }
        return badChar; 
    }
    // Preprocessing for the good suffix heuristics 
    private int[] preprocessGoodSuffix(String pattern){
        int m=pattern.length(); 
        int[] goodSuffix = new int[m]; // To store the shift distance for each position
        int[] borderPos = new int[m+1];  // To store the border position

        int i=m, j=m+1; 
        borderPos[i]=j; 

        while(i>0){
            while(j<=m && pattern.charAt(i-1) != pattern.charAt(j-1)){
                if(goodSuffix[j-1] ==0){
                    goodSuffix[j-1]=j-i; 
                }
                j=borderPos[j];
            }
            i--; 
            j--; 
            borderPos[i]=j; 
    }
    for(i=0; i<m; i++){
        if(goodSuffix[i]==0){
            goodSuffix[i]=j;
        }
        if(i==j){
            j=borderPos[j]; 
        }
    }
    return goodSuffix;

    }
    public static void main(String[] args){
        Solution sol1=new Solution(); 
        String haystack = "sadbutsad";
        String needle = "sad";
        int result=sol1.strStr(haystack, needle);
        System.out.println("1st occurrence Index: "+result);
    }
}