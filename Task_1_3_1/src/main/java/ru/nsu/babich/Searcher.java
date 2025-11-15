package ru.nsu.babich;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the Knuth-Morris-Pratt (KMP) algorithm to find substrings in the text.
 */
public class Searcher {

    /**
     * Finds all occurrences of a word in a file using KMP algorithm.
     *
     * @param filename Path to the file to search in.
     * @param word The pattern to search for.
     * @return The list of occurrence start indices.
     */
    public static List<Long> find(String filename, String word) {
        List<Long> result = new ArrayList<>();
        int[] table = computeFailureFunction(word);
        long j = 0;
        int k = 0;
        try (var reader = new BufferedReader(new FileReader(filename))) {
            var symbol = reader.read();
            while (symbol != -1) {
                var ch = (char) symbol;
                while (k >= 0 && ch != word.charAt(k)) {
                    k = table[k];
                }
                j++;
                k++;
                if (k == word.length()) {
                    result.add(j - k);
                    k = table[k];
                }
                symbol = reader.read();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Computes the KMP failure function (partial match table).
     *
     * @param word A word to search.
     * @return A computed table.
     */
    private static int[] computeFailureFunction(String word) {
        var table = new int[word.length() + 1];
        table[0] = -1;
        var pos = 1;
        var cnd = 0;
        while (pos < word.length()) {
            if (word.charAt(pos) == word.charAt(cnd)) {
                table[pos] = table[cnd];
            } else {
                table[pos] = cnd;
                while (cnd >= 0 && word.charAt(pos) != word.charAt(cnd)) {
                    cnd = table[cnd];
                }
            }
            pos++;
            cnd++;
        }
        table[pos] = cnd;
        return table;
    }
}
