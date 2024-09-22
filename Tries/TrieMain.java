public class TrieMain{
    public static void main(String[] args){
        Trie trie=new Trie(); 
        trie.insert("apple"); 
        trie.insert("app"); 
        // Searching for words 
        System.out.println(trie.search("apple")); 
        System.out.println(trie.search("app"));
        System.out.println(trie.search("appl")); 
        // Searching for prefixes 
        System.out.println(trie.startsWith("app")); 
        System.out.println(trie.startsWith("apl")); 
        
    }
}