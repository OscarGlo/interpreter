package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Reader {
    public static TrieNode<String> getTrie(String path) throws FileNotFoundException {
        TrieNode<String> trie = new TrieNode<>();

        Scanner sc = new Scanner(new File(path));

        for (int n = 1; sc.hasNextLine(); n++) {
            String line = sc.nextLine();

            // Don't parse comments or empty lines
            if (line.matches("\\s*(#.*)?"))
                continue;

            // If no tab is found, throw an exception
            if (line.indexOf('\t') < 0)
                throw new RuntimeException("Invalid line format: no tab character found (" + path + ":" + n + ")");

            String[] sp = line.split("\\t");
            trie.put(sp[0], sp[1]);
        }

        return trie;
    }
}
