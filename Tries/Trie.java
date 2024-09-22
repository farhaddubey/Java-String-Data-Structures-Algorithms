// Trie Class with Insert, Search, Prefix methods 
public class Trie{
    private TrieNode root; 
    public Trie(){
        root=new TrieNode(); // Initializing the root as TrieNode 
    }
    
    //Method to insert a word in Trie 
    public void insert(String word){
        TrieNode current=root; 
        for(char c: word.toCharArray()){
            int index=c-'a'; // Getting the index from 0-25 for the characters a-'a'==0.........25 
            if(current.children[index]==null){
                current.children[index]=new TrieNode(); // Creating a new Trie Node if not present 
            }
            current=current.children[index]; // Moving to the child node 
        }
        // After inserting all characters, marking the last node as the end of the word 
        current.isEndOfWord = true; 
    }

    // Method to search for a word in the trie 
    public boolean search(String word){
        TrieNode current=root; 
        for(char c: word.toCharArray()){
            int index=c-'a';   
            if(current.children[index]==null){
                return false; // word is not present 
            }
            current=current.children[index]; 
        }
        return current.isEndOfWord; // Checking if that's the end of the word 
    }

    // Method to check if prefix exists in the Trie 
    public boolean startsWith(String prefix){
        TrieNode current=root; 
        for(char c: prefix.toCharArray()){
            int index=c-'a'; 
            if(current.children[index]==null){
                return false; // Prefix is not present 
            }
            current=current.children[index]; 
        }
        return true; // All characters of the prefix were found.. 
    }
}