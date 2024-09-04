public class BoyerMoore3 {
    // Preprocessing for the bad character heuristic
    private int[] preprocessBadCharacter(String pattern) {
        int[] badChar = new int[256]; // Size of ASCII charset
        for (int i = 0; i < 256; i++) {
            badChar[i] = -1; // Initialize all to -1
        }
        for (int i = 0; i < pattern.length(); i++) {
            badChar[pattern.charAt(i)] = i; // Fill with last occurrence index
        }
        return badChar;
    }

    // Preprocessing for the good suffix heuristic
    private int[] preprocessGoodSuffix(String pattern) {
        int m = pattern.length();
        int[] goodSuffix = new int[m];
        int[] borderPos = new int[m + 1];
        
        int i = m, j = m + 1;
        borderPos[i] = j;

        while (i > 0) {
            while (j <= m && pattern.charAt(i - 1) != pattern.charAt(j - 1)) {
                if (goodSuffix[j - 1] == 0) {
                    goodSuffix[j - 1] = j - i;
                }
                j = borderPos[j];
            }
            i--;
            j--;
            borderPos[i] = j;
        }

        for (i = 0; i < m; i++) {
            if (goodSuffix[i] == 0) {
                goodSuffix[i] = j;
            }
            if (i == j) {
                j = borderPos[j];
            }
        }

        return goodSuffix;
    }

    // Boyer-Moore search algorithm
    public int search(String text, String pattern) {
        int m = pattern.length();
        int n = text.length();

        int[] badChar = preprocessBadCharacter(pattern);
        int[] goodSuffix = preprocessGoodSuffix(pattern);

        int shift = 0;

        while (shift <= (n - m)) {
            int j = m - 1;

            // Start comparing from the end of the pattern
            while (j >= 0 && pattern.charAt(j) == text.charAt(shift + j)) {
                j--;
            }

            if (j < 0) {
                System.out.println("Pattern found at index: " + shift);
                shift += (shift + m < n) ? m - badChar[text.charAt(shift + m)] : 1;
            } else {
                // Shift pattern based on the badChar rule or goodSuffix rule
                shift += Math.max(1, Math.max(j - badChar[text.charAt(shift + j)], goodSuffix[j]));
            }
        }
        return -1; // Pattern not found
    }

    public static void main(String[] args) {
        BoyerMoore3 bm = new BoyerMoore3();
        String text = "abacaabadcabacabaabb";
        String pattern = "abacab";
        int result = bm.search(text, pattern);
        if (result != -1) {
            System.out.println("Pattern found at index: " + result);
        } else {
            System.out.println("Pattern not found.");
        }
    }
}
