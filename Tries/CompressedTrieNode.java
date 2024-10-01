import java.util.Map; 
import java.util.HashMap; 

public class CompressedTrieNode{
    String value; 
    Map<Character, CompressedTrieNode> children; 
    boolean isEndOfWord; 
    public CompressedTrieNode(String value){
        this.value=value; 
        this.children=new HashMap<>(); 
        this.isEndOfWord=false;
    }
}