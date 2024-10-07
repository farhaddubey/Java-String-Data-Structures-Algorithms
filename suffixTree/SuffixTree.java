import java.util.*;

public class SuffixTree{
    private String text; 
    private SuffixTreeNode root; 
    private int currentEnd; // For marking the end of each phase 
    private SuffixTreeNode lastCreatedInternalNode; // Used for linking new Internal Nodes 

    // Active point helps in knowing where the next insertion starts 
    private SuffixTreeNode activeNode; 
    private int activeEdge; 
    private int activeLength;

    // Constructor to initialize tree and build it. 
    public SuffixTree(String text){
        this.text=text; 
        this.root=new SuffixTreeNode(-1, -1); // Root has no characters 
        this.activeNode=root; // Starting with the root as the active node 
        this.activeEdge=-1; // Initially No active edge 
        this.activeLength=0; // No active lengths initially 
        this.currentEnd=-1; // Keeps track of the current node 
        this.lastCreatedInternalNode=null; // No internal nodes initially

        // building the Suffix Tree 
        buildSuffixTree();
    }

    //Method to build the Suffix Tree 
    private void buildSuffixTree(){
        for(int i=0; i<text.length(); i++){
            extendSuffixTree(i);
        }
    }

    //Method to extend the suffix tree by adding characters one by one 
    private void extendSuffixTree(int pos){
        currentEnd++; // Increment the phase
        // Keeping track of how many extensions are needed for the current phase 
        lastCreatedInternalNode=null; // Reset at the start of each phase 

        // Looping over all characters in the suffixes of the current phase 
        for(int remainingSuffixes=0; remainingSuffixes<=pos; remainingSuffixes++){
            // If active length is 0, Updating the active Edge to the current character 
            if(activeLength==0){
                activeEdge=pos; // Starting newEdge 
            }

            // Checking if ther's no edge starting with the active character 
            char activeChar=text.charAt(activeEdge); 
            if(!activeEdge.children.containsKey(activeChar)){
                // No edge exist create a new leaf node 
                activeNode.current.containsKey(activeChar, new SuffixTreeNode(pos, text.length()));

                // If there was a previously created internal node, link it via suffixLink 
                if(lastCreatedInternalNode!=null){
                    lastCreatedInternalNode.suffixLink=activeNode; 
                    lastCreatedInternalNode = null;
                }
            }else{
                // The character already exists traversing down the edge 
                SuffixTreeNode nextNode =activeNode.children.get(activeChar);
                int edgeLength = nextNode.end - nextNode.start; 
                // If activeLength exceeds or matches the edgeLength, go down to the next node 
                if(activeLength>=edgeLength){
                    activeNode=nextNode;  
                    activeLength-=edgeLength; 
                    activeEdge+=edgeLength; 
                    continue; // Moving to the next character 
                }

                // If the character on the Edge matches, just increment activeLength and move on 
                if(text.charAt(nextNode.start+activeLength)==text.charAt(pos)){
                    activeLength++;
                    // Linking the last internal node created if there's any
                    if(lastCreatedInternalNode!=null){
                        lastCreatedInternalNode.suffixLink=activeNode;
                        lastCreatedInternalNode=null;
                    }
                    break; // No need for further extension
                }

                //Otherwise, we need to split the edge and create a new Internal node
                SuffixTreeNode splitNode = new SuffixTreeNode(nextNode.start, nextNode.start + activeLength);
                activeNode.children.put(activeChar, splitNode); // Updating the current node to point to the split node 
                splitNode.children.put(text.charAt(pos), new SuffixTreeNode(pos, text.length())); // New leaf Node 
            }
        }
    }
}