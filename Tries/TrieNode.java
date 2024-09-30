public class TrieNode{
    TrieNode[] children=new TrieNode[26];  // Assuming using onlys small case english letters a-z 
    boolean isEndOfWord=false; 
    TrieNode(){
        for(int i=0; i<26; i++){
            children[i]=null;   
        }
    }
}