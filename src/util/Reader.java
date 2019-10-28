package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

public class Reader {
    private static void readFile(Consumer<String[]> put, String path) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(path));

        for (int n = 1; sc.hasNextLine(); n++) {
            String line = sc.nextLine();

            // Don't parse comments or empty lines
            if (line.matches("\\s*(#.*)?"))
                continue;

            // If no tab is found, throw an exception
            if (line.indexOf('\t') < 0)
                throw new RuntimeException("Invalid line format: no tab character found (line " + n + ")");

            // Remove trailing comments
            if (line.contains("#"))
                line= line.substring(0, line.indexOf('#'));

            // Trim start & end
            line = line.replaceAll("(^\\s+|\\s+$)", "");

            put.accept(line.split("\\t"));
        }
    }

    public static TrieNode<String> getTrie(String path) throws FileNotFoundException {
        TrieNode<String> trie = new TrieNode<>();

        readFile(strs -> trie.put(strs[0], strs[1]), path);

        return trie;
    }

    public static Map<String, String> getMap(String path) throws FileNotFoundException {
        Map<String, String> map = new LinkedHashMap<>();

        readFile(strs -> map.put(strs[0], strs[1]), path);

        return map;
    }
}
