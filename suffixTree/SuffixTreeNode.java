import java.util.HashMap; 

class SuffixTreeNode{
    HashMap<Character, SuffixTreeNode> children=new HashMap<>(); 
    int start, end; 
    SuffixTreeNode suffixLink; 
    // Construction for a Node 
    public SuffixTreeNode(int start, int end){
        this.start=start; 
        this.end=end; 
        this.suffixLink=suffixLink; 
    }
}