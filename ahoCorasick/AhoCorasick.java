import java.util.*;

class AhoCorasick {
    private static class Node {
        Map<Character, Node> children = new HashMap<>();
        Node failLink = null;
        List<String> output = new ArrayList<>();
    }

    private Node root = new Node();

    // Build the Trie from patterns
    public void buildTrie(String[] patterns) {
        for (String pattern : patterns) {
            Node current = root;
            for (char ch : pattern.toCharArray()) {
                current = current.children.computeIfAbsent(ch, c -> new Node());
            }
            current.output.add(pattern);
        }
    }

    // Build Failure Links
    public void buildFailureLinks() {
        Queue<Node> queue = new LinkedList<>();
        root.failLink = root;
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            for (Map.Entry<Character, Node> entry : current.children.entrySet()) {
                char ch = entry.getKey();
                Node child = entry.getValue();

                Node fail = current.failLink;
                while (fail != root && !fail.children.containsKey(ch)) {
                    fail = fail.failLink;
                }

                if (fail.children.containsKey(ch)) {
                    child.failLink = fail.children.get(ch);
                } else {
                    child.failLink = root;
                }

                child.output.addAll(child.failLink.output);
                queue.add(child);
            }
        }
    }

    // Search Text
    public List<String> search(String text) {
        List<String> matches = new ArrayList<>();
        Node current = root;

        for (char ch : text.toCharArray()) {
            while (current != root && !current.children.containsKey(ch)) {
                current = current.failLink;
            }

            if (current.children.containsKey(ch)) {
                current = current.children.get(ch);
            }

            matches.addAll(current.output);
        }

        return matches;
    }

    public static void main(String[] args) {
        AhoCorasick ac = new AhoCorasick();
        String[] patterns = {"he", "she", "his", "hers"};
        ac.buildTrie(patterns);
        ac.buildFailureLinks();

        String text = "ahishers";
        List<String> results = ac.search(text);
        System.out.println("Matched patterns: " + results);
    }
}
