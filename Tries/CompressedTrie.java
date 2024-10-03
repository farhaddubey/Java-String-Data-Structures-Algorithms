import java.util.Map; 
import java.util.HashMap; 

class CompressedTrie {
    // The root of the Trie, initially an empty node
    private CompressedTrieNode root;
    public CompressedTrie(){
        root=new CompressedTrieNode("");
    }
    // Insert method to add a word to the compressed Trie 
    public void insert(String word){
        CompressedTrieNode current=root; // Start traversing from the root node
        int index=0; // Starting with the 1st character of the word
        // Looping through the word until all characters are processed
        while(index<word.length()){
            char currentChar=word.charAt(index);
            // Checking if there's already a child node with that character
            if(current.children.containsKey(currentChar)){
                // If there's a matching child node, then fetching all attributes of that char from the mapping 
                CompressedTrieNode child=current.children.get(currentChar);
                // Now extracting the value of the child node 
                String childNode=child.value;

                // Now finding the common prefix length between the current word and the child's node values 
                int commonPrefixLength=findCommonPrefixLength(word.substring(index), childValue);


                // If the common prefix is the entire child node value, continue traversing down
                if(commonPrefixLength==childValue.length()){
                    // Moving the index forward by the length of the common prefix 
                    index+=commonPrefixLength;
                    // Updating the current pointer to the child node as required 
                    current=child;
                }else{
                    // If only part of the child node value matches, we need to split the node 
                    // The common prefix between the word and the child node 
                    String commonPrefix=childValue.substring(0, commonPrefixLength);
                    // The remaining part of the child nodes value 
                    String suffix=childValue.substring(commonPrefixLength);

                    // Creating a newnode for the common prefix by the common prefix using as value 
                    CompressedTrieNode newNode =new CompressedTrieNode(commonPrefix);
                    // Replacing the child node in the current node's children with the newnode
                    current.children.put(currentChar, newNode);

                    // The suffix becomes a child of the newNode 
                    newNode.children.put(suffix.charAt(0), new CompressedTrieNode(suffix));
                    // Transfer the children and endOfWord marker from the old child to the suffix node 
                    newNode.children.get(suffix.charAt(0)).children=child.children; 
                    newnode.children.get(suffix.charAt(0)).isEndOfWord=child.isEndOfWord; 
                    // Now the current pointer moves to the newNode (common prefix node)
                    current=newNode;
                    // Updating the index to skip the matched prefix 
                    index+=commonPrefixLength;
                }
            }else{
                // If no matching child is found creating a newNode for the remaining part of the word
                current.children.put(currentChar, new CompressedTrieNode(word.substring(index)));
                // Marking the newly added node as the end of the word 
                current.children.get(currentChar).isEndOfWord=true;
                // Ending the method as the word has been fully inserted 
                return;
            }
        }
        // Once we exit the loop, marking the current node as the end of the word 
        current.isEndOfWord = true;
    }
    // Helper function to find the common prefix length between two strinngs 
    private int commonPrefixLength(String wordPart, String nodeValue){
        int length=Math.min(wordPart.length(), nodeValue.length());
        int i=0; 
        while(i<length && wordPart.charAt(i)==nodeValue.charAt(i)){
            i++;
        }
        return i;
    }
}