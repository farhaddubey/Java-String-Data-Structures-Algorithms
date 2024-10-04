import java.util.Map; 
import java.util.HashMap;

class CompressedTrie{
    private CompressedTrieNode root; 
    public CompressedTrie(){
        root=new CompressedTrieNode("");
    }
    // Insert method for the compressed Trie 
    public void insert(String word){
        CompressedTrieNode current=root; 
        int index=0; 

        // Continue until all characters of the words are inserted 
        while(index<word.length()){
            char currentChar=word.charAt(index); // Getting the current char 
            // Checking if the current character exists in the children of the current node
            if(current.children.containsKey(currentChar)){
                CompressedTrieNode child=current.children.get(currentChar); // Get the child node 
                String childValue =child.value;

                // Finding the length of the common prefix between the word(starting at index) and childValue
                int commonPrefixLength=findCommonPrefixLength(word.substring(index), childValue);

                // If the entire child value matches the prefix of the word 
                if(commonPrefixLength==childValue.length()){
                    index+=commonPrefixLength; // Moving the index forward by the length of the matched prefix 
                    current=child; /// Updating the current to child
                }else{
                    // Splitting the child node at the point of mismatch 
                    String commonPrefix=childValue.substring(0, commonPrefixLength); // Extracted the commmon prefix
                    String suffix=childValue.substring(commonPrefixLength); // Remaining part of the child value 

                    // Creating a newNode for the common prefix and replacing the original child 
                    CompressedTrieNode newNode=new CompressedTrieNode(commonPrefix);
                    current.children.put(currentChar, newNode);

                    // The old child becomes a child of the new Node 
                    newNode.children.put(suffix.charAt(0), new CompressedTrieNode(suffix));
                    newNode.children.get(suffix.charAt(0)).children=child.children; 
                    newNode.children.get(suffix.charAt(0)).isEndOfWord=child.isEndOfWord;

                    current=newNode;// Updating or Moving down to the current Node 
                    index+=commonPrefixLength; // Moving the index forward by the length of the common Prefix 
                }
            }else{
                // If no child matches the current Character, add a newNode for the remaining word 
                current.children.put(currentChar, new CompressedTrieNode(word.substring(index)));
                current.children.get(currentChar).isEndOfWord=true; // Marking as the end of the word 
                return; // Insertion complete 
            }
        }
        current.isEndOfWord=true;
    }
    private int findCommonPrefixLength(String wordPart, String nodeValue){
        int value=Math.min(wordPart.length(), nodeValue.length());
        int index=0;
        while(index<value && wordPart.charAt(index)==nodeValue.charAt(index)){
            index++;
        }
        return index;
    }
}