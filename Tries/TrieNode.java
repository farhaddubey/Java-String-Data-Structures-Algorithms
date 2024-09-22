// Creating a Trie Node Structure: 
// Each Trie Node contains an array for storing the children nodes & a boolean value indicating whether   
// if that's the end of the nodes  
class TrieNode{
    TrieNode[] children=new TrieNode[26]; // Assuming small english letters 
    boolean isEndOfWord = false; 
    TrieNode(){
        for(int i=0; i<26; i++){
            children[i]=null;
        }
    }
}