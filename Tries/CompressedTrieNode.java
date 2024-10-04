import java.util.Map; 
import java.util.HashMap; 

public class CompressedTrieNode{
    String value; 
    boolean isEndOfWord; 
    Map<Character, CompressedTrieNode> children;
    public CompressedTrieNode(String value){
        this.value=value;
        this.children=new HashMap<>();
        this.isEndOfWord=false;
    }
}