
class KMPAlgorithm{
    // Method to build the LPS array 
    private int[] buildLPS(String pattern){
        int m=pattern.length(); 
        int[] lps=new int[m]; 
        int length=0; // At 0th index length of LPS is always 0 
        int i=1; // LPS[0] is always 0 that's why starting from index 1 
        lps[0]=0;
        // Building the LPS array 
        while(i<m){
            if(pattern.charAt(i)==pattern.charAt(length)){
                length++;
                lps[i]=length; 
                i++;
            }else{
                if(length!=0){
                    //falling back to the previous longest prefix suffix 
                    length=lps[length-1]; 
                }else{
                    lps[i]=0;
                    i++;
                }
            }
        }
        return lps;
    }
    public int KMPSearch(String text, String pattern){
        int n=text.length(); 
        int m=pattern.length(); 
        // Edge case if the pattern is empty returning 0 
        if(m==0) return 0; 
        // Building the LPS array for the pattern 
        int[] lps=buildLPS(pattern);
        int i=0; // index for the text 
        int j=0; // index for the pattern 
        while(i<n){
            if(pattern.charAt(j)==text.charAt(i)){
                i++; 
                j++;
            }
            if(j==m){
                return i-j; // Pattern found at index i-j;
            }
            else if(i<n && pattern.charAt(j)!=text.charAt(i)){
                // Scenarios such as mismatch after j matches 
                if(j!=0){
                    j=lps[j-1]; // Using the LPS array to skip comparisons 
                }else{
                    i++;
                }
            }
        }
        return -1;
    }
    public static void main(String[] args){
        KMPAlgorithm kmp=new KMPAlgorithm(); 
        String text="ABABDABACDABABCABAB"; 
        String pattern="ABABCABAB"; 
        int result=kmp.KMPSearch(text, pattern); 
        if(result!=-1){
            System.out.println("Pattern found at index: "+result);
        }else{
            System.out.println("Pattern not found.");
        }
    }
}