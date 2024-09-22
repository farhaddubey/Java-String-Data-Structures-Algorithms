class TrieNode{
    TrieNode[] children=new TrieNode[26]; 
    boolean isEndOfWord=false; 
    TrieNode(){
        for(int i=0; i<26; i++){
        children[i]=false;
    }
    }
}
public class Trie{
    private TrieNode root; 
    public Trie(){
        root =new TrieNode(); // Initializing the root node for whenever the object of Trie is created 
    }
    // Method to insert a word in the Trie
    public void insert(String word){
        System.out.println("Inserting the word: "+word); 
        TrieNode current=root; 

        for(char c: word.toCharArray()){
            int index=c-'a'; // Getting the index from 0-25 for the character 
            System.out.println("Processing charcter: "+c+"at index: "+index);
            if(current.children[index]==null){
                current.children[index]=new TrieNode(); // Creating a new node if not present 
                System.out.println("Created new node for: "+c);
            }else{
                System.out.println("Node for "+c+" already exists");
            }
            current=current.children[index]; // Moving to the child Node 
        }
        // After inserting all characters, markign the last node as the end of the node 
        current.isEndOfWord=true; 
        System.out.println("Word "+word+" inserted successfully");
    }
    // Method to search for a word in the Trie
    public boolean search(String word){
        System.out.println("Searching for the word: "+word); 
        TrieNode current = root; 

        for(char c: word.toCharArray()){
            int index=c-'a'; 
            System.out.println("Processing the character: "+c+"at index"+index); 
            if(current.children[index]==null){
                System.out.println("Character"+c+"not found. Word doesn't exist.");
                return false; // Word is not present 
            }
            current = current.children[index]; 
        }
        if(current.isEndOfWord){
            System.out.println("Word: "+word+"Found");
        }else{
            System.out.println("Prefix found, but: "+word+" is not complete word. \n");
        }
        return current.isEndOfWord; // Check if it's the end of the word.
    }

    // Method to check if prefix exists in the word 
    public boolean startsWith(String prefix){
        System.out.println("Checking prefix: "+prefix); 
        TrieNode current = root; 

        for(char c: prefix.toCharArray()){
            int index= c-'a'; // 0------------->25; 
            System.out.println("processing charcter"+c+"at index: "+index); 
            if(current.children[index]==null){
                System.out.println("Charcter "+c+"not found ");
                return false; // Prefix not found 
            }
            current=current.children[index]; 
        }
        System.out.println("Prefix: "+prefix+"exists in the Trie."); 
        return true; // All Charcters of the Trie were found. 
    }
}

public class Main{
    public static void main(String[] args){
        Trie trie=new Trie(); 
        // Inserting words into the Trie with visual output 
        trie.insert("apple"); 
        trie.insert("app"); 
        // Searching for words with visual output 
        trie.search("apple"); 
        trie.search("app"); 
        trie.search("appl"); 
        // Searching for prefixes with visual output
        trie.startsWith("app"); 
        trie.startsWith("apl"); 
    }
}