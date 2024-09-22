import java.util.*;

public class RabinKarpClassic {

    // d is the number of characters in the input alphabet (256 for ASCII)
    private final static int d = 256;

    /**
     * Function to search for a pattern in the given text using Rabin-Karp algorithm
     * @param pattern The pattern string to search for
     * @param text The text string in which the pattern is searched
     * @param q A prime number for hashing
     */
    public static void search(String pattern, String text, int q) {
        int m = pattern.length();
        int n = text.length();
        int i, j;
        int p = 0; // hash value for pattern
        int t = 0; // hash value for text
        int h = 1;

        // The value of h would be "pow(d, m-1)%q"
        for (i = 0; i < m - 1; i++)
            h = (h * d) % q;

        // Calculate the hash value of pattern and first window of text
        for (i = 0; i < m; i++) {
            p = (d * p + pattern.charAt(i)) % q;
            t = (d * t + text.charAt(i)) % q;
        }

        // Slide the pattern over text one by one
        for (i = 0; i <= n - m; i++) {

            // If the hash values match, then only check characters one by one
            if (p == t) {
                boolean match = true;
                for (j = 0; j < m; j++) {
                    if (text.charAt(i + j) != pattern.charAt(j)) {
                        match = false;
                        break;
                    }
                }
                // If p == t and pattern[0...m-1] == text[i...i+m-1]
                if (match) {
                    System.out.println("Pattern found at index " + i);
                }
            }

            // Calculate hash value for next window of text: Remove leading digit,
            // add trailing digit
            if (i < n - m) {
                t = (d * (t - text.charAt(i) * h) + text.charAt(i + m)) % q;

                // We might get negative value of t, converting it to positive
                if (t < 0)
                    t = (t + q);
            }
        }
    }

    public static void main(String[] args) {
        String text = "ABCCDDAEFG";
        String pattern = "CDD";
        int q = 101; // A prime number

        search(pattern, text, q);
    }
}
