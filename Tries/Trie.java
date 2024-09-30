public class Trie{
    private TrieNode root; // Just declared the pointer a reference basically
    public Trie(){
        root=new TrieNode(); // initailzing the node 
    } 

    // Method to insert a word into the Trie 
    public void insert(String word){
        TrieNode current=root;
        for(char c: word.toCharArray()){
            int index=c-'a'; //    67-65=2   a->0 b->1 c->2 0------------------25 
            if(current.children[index]==null){
                current.children[index]=new TrieNode(); 
            }
            current=current.children[index]; 
        }
        current.isEndOfWord=true; 
    }

    // Method to search a word into the Trie 
    public boolean search(String word){
        TrieNode current=root; 
        for(char c: word.toCharArray()){
            int index=c-'a'; 
            if(current.children[index]==null){
                return false;
            }
            current=current.children[index];
        }
        return current.isEndOfWord;
    }

    // Method to check if a prefix exsits int the Trie 
    public boolean startsWith(String prefix){
        TrieNode current=root; 
        for(char c: prefix.toCharArray()){
            int index=c-'a'; 
            if(current.children[index]==null){
                return false; // Pattern of prefix do not exists 
            }
            current=current.children[index]; 
        }
        return true; // All charcters of the prefix exists so returnign true. 
    }

    public static void main(String[] args){
        Trie trie=new Trie(); 
        // IInseting words 
        trie.insert("apple"); 
        trie.insert("app"); 
        trie.insert("mango"); 
        // Searching words 
        System.out.println(trie.search("apple"));  //true; 
        System.out.println(trie.search("applemobile")); //false;
        
        System.out.println(trie.startsWith("man"));
    }
}