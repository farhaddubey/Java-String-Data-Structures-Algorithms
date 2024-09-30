A Compressed Trie (also known as a Patricia Trie or Radix Tree) is an optimization of the standard Trie (prefix tree). In a traditional Trie, each character of a word is stored in a separate node, but a compressed Trie eliminates nodes that have only one child by merging them into a single edge. This compression reduces space complexity, making the structure more memory efficient, especially when dealing with long strings or large datasets that share common prefixes.

Key Concepts of Compressed Trie
Compression: In a normal Trie, every character is stored in a separate node. In a compressed Trie, if a node has only one child, that node and its child are merged into a single edge, with the entire substring stored in one go.

Space Efficiency: A compressed Trie reduces the number of nodes, making the tree shallower and more efficient in terms of both space and traversal time.

Usage: Compressed Tries are useful in applications such as IP routing, autocomplete, spell-checkers, and databases where prefixes need to be handled efficiently.

Differences Between Standard and Compressed Trie
Standard Trie: Each node represents a single character.
Compressed Trie: Each node can represent a string of characters (a substring), reducing the height of the tree.
Why Use Compressed Trie?
Space Optimization: The compressed Trie saves space by collapsing chains of single-child nodes.
Faster Operations: With fewer nodes, operations like search, insert, and delete become faster.
Efficient with Similar Prefixes: When you have a dataset with many common prefixes, compressed Tries shine, as they compress these prefixes into fewer nodes.
How it Works
Consider you are inserting words into a Trie. If a sequence of nodes only has one child, instead of creating separate nodes for each character, you merge them into a single node that stores the entire sequence.

Example of a Compressed Trie
Let's look at an example of inserting words "apple", "app", and "apt" into a compressed Trie:

Standard Trie Representation:

arduino
Copy code
            (root)
              |
             'a'
              |
             'p'
             / \
            'p' 't'
             |
            'l'
             |
            'e'
In this case, "app" and "apple" share the same prefix, and "apt" shares only the first two characters.

Compressed Trie Representation:

arduino
Copy code
            (root)
              |
             'ap'
            /   \
          'p'   't'
           |
          'le'
Explanation:

Instead of splitting "ap" into two nodes 'a' and 'p', we combine them into one.
Similarly, the words "apple" and "app" now share the node 'p', and "le" is attached at the end to complete the word "apple".
The word "apt" diverges from "ap" and completes with the node 't'.
Operations on a Compressed Trie
1. Insertion:
Inserting a new word requires splitting nodes if part of the word matches an existing path but then diverges. This ensures that nodes represent the longest common prefix.

2. Search:
To search for a word, you traverse the Trie by comparing entire substrings at each node instead of individual characters.

Code Implementation of a Compressed Trie in Java
Here's a simplified Java implementation of a Compressed Trie.

java
Copy code
class CompressedTrieNode {
    String value;            // The string this node represents (substring)
    HashMap<Character, CompressedTrieNode> children;  // Map of children nodes
    boolean isEndOfWord;     // Marks if this node represents the end of a word

    public CompressedTrieNode(String value) {
        this.value = value;
        this.children = new HashMap<>();
        this.isEndOfWord = false;
    }
}

public class CompressedTrie {
    private CompressedTrieNode root;

    public CompressedTrie() {
        this.root = new CompressedTrieNode("");  // Root node with empty string
    }

    // Insert a word into the compressed Trie
    public void insert(String word) {
        CompressedTrieNode current = root;
        int index = 0;

        while (index < word.length()) {
            char currentChar = word.charAt(index);
            CompressedTrieNode child = current.children.get(currentChar);

            if (child == null) {
                // No match, so add a new node for the remaining part of the word
                current.children.put(currentChar, new CompressedTrieNode(word.substring(index)));
                return;
            }

            // Find the common prefix between word and existing node
            String childValue = child.value;
            int matchLength = commonPrefixLength(word.substring(index), childValue);

            if (matchLength == childValue.length()) {
                // Full match, continue down this branch
                current = child;
                index += matchLength;
            } else {
                // Partial match, split the node
                CompressedTrieNode splitNode = new CompressedTrieNode(childValue.substring(matchLength));
                splitNode.children.putAll(child.children);
                splitNode.isEndOfWord = child.isEndOfWord;

                // Update the current child to reflect the split
                child.value = childValue.substring(0, matchLength);
                child.children.clear();
                child.children.put(splitNode.value.charAt(0), splitNode);

                if (matchLength < word.length() - index) {
                    // Add the new part of the word
                    child.children.put(word.charAt(index + matchLength), new CompressedTrieNode(word.substring(index + matchLength)));
                } else {
                    child.isEndOfWord = true;  // If word ends here
                }
                return;
            }
        }
        current.isEndOfWord = true;  // Mark the last node as end of word
    }

    // Search a word in the compressed Trie
    public boolean search(String word) {
        CompressedTrieNode current = root;
        int index = 0;

        while (index < word.length()) {
            char currentChar = word.charAt(index);
            CompressedTrieNode child = current.children.get(currentChar);

            if (child == null) {
                return false;  // Word not found
            }

            String childValue = child.value;
            if (!word.startsWith(childValue, index)) {
                return false;  // Mismatch in the middle of a string
            }

            index += childValue.length();
            current = child;
        }
        return current.isEndOfWord;
    }

    // Helper method to find the length of the common prefix between two strings
    private int commonPrefixLength(String s1, String s2) {
        int length = Math.min(s1.length(), s2.length());
        for (int i = 0; i < length; i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                return i;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        CompressedTrie trie = new CompressedTrie();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("apt");

        System.out.println(trie.search("apple"));  // true
        System.out.println(trie.search("app"));    // true
        System.out.println(trie.search("apt"));    // true
        System.out.println(trie.search("bat"));    // false
    }
}
Explanation of the Code
CompressedTrieNode: Represents each node in the compressed Trie. It stores the string value (a substring), its children, and whether it marks the end of a word.
insert method: Inserts a word by traversing the tree, finding common prefixes, and splitting nodes as needed.
search method: Searches for a word by traversing the Trie, matching substrings at each node.
commonPrefixLength: A helper function to find the length of the common prefix between two strings.
Conclusion
A Compressed Trie is an optimized version of the regular Trie that reduces the number of nodes by collapsing chains of nodes with only one child. It is useful when dealing with large datasets with many shared prefixes, providing both space and time efficiency for operations like insert and search.

Compressed Tries are particularly helpful in applications such as:

IP routing tables
Autocomplete features
Spell checkers