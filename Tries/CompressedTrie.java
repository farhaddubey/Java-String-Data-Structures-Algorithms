import java.util.Map; 
import java.util.HashMap;

// Defining the compressedTrie class 
class CompressedTrie{
    // The root of the trie, initially an empty node 
    private CompressedTrieNode root;
    public CompressedTrie(){
        root=new CompressedTrie("");
    }
    // Insert method to add a word to the compressed Trie 
    public void insert(String word){
        // Start traversing from the root node 
        CompressedTrieNode current=root; 
        // Starting with the first charcter of the word (index 0)
        int index=0;
        // Looping through the word until all characters are processed
        while(index<word.length()){
            // Getting the character at the current index
            char currentChar=word.charAt(index);

            // Checking if there's already a child node starting with this character 
            if(current.children.contains(currentChar)){
                // If there's a matching child node fetch it 
                CompressedTrieNode child=current.children.get(currentChar);
                // The value of the child node (compressed String)
                String childValue=child.value;
                // Finding the common prefix length between the current word part and the child node's value
                int commonPrefixLength=findCommonPrefixLength(word.substring(index), childValue); 
                // If the common prefix is the entire child node value, continue traversing down 
                if(commonPrefixLength==childValue.length()){
                    // Moving the index forward by the length of the common prefix 
                    index += commonPrefixLength; 
                    // Moving the current pointer to the child node 
                    current = child;
                }else{
                    // If only part of the child node value matches, we need to split the child node 
                    // The common prefix between the word and the child node 
                    String commonPrefix =childValue.substring(0, commonPrefixLength);
                    // The remaining part of the child node's value (suffix)
                    String suffix = childValue.substring(commonPrefixLength); 

                    // Creating a newnode for the common prefix 
                    CompressedTrieNode newNode =new CompressedTrieNode(commonPrefix);
                    // Replacing the child node in the current node's children with the newnode 
                    current.children.put(currentChar, newNode);

                    // The suffix becomes the child node of the tree 
                    newNode.children.put(suffix.charAt(0), new CompressedTrieNode(suffix));
                    // Transfering the children and endOftheWord marker from the old child to the suffix node 
                    newNode.children.get(suffix.charAt(0)).children = child.children; 
                    newNode.children.get(suffix.charAt(0)).isEndOfWord=child.isEndOfWord; 
                    // Updating the current pointer to point to the next created node 
                    current=newNode;
                    // Updating the index to skip the matched Prefix 
                    index += commonPrefixLength;
                }
            }else{
                // If no matching child is found, create a newNode, for the remaining part of the word,
                current.children.put(currentChar, new CompressedTrieNode(word.substring(index)));
                // Mark the newly added node as the end of a word 
                current.children.get(currentChar).isEndOfWord = true;
                // End the method as the word has been fully inserted 
                return; 
            }
        }
        // Once we exit the loop, mark the current node as the end of the word 
        current.isEndOfWord=true;
    }

    // Helper function to find the common prefix length between two strings 
    private int findCommonPrefixLength(String wordPart, String nodeValue){
        // Finding the minimum length between the two strings to avoid index out of bounds error
        int length= Math.min(wordPart.length(), nodeValue.length()); 
        // Initialize index i to 0, which will be used to count the common length 
        int i=0; 
        // Looping while characters at same position in both strings match 
        while(i<length && wordPart.charAt(i)==nodeValue.charAt(i)){
            i++; // Incrementing i for each matching character 
        }
        // Returning the length of the common prefix 
        return i;
    }
}