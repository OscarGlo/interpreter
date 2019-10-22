package util;

import java.util.HashMap;
import java.util.Map;

public class TrieNode<T> {
    private T elem;
    private Map<Character, TrieNode<T>> children;

    public TrieNode(T elem) {
        this.elem = elem;
        children = new HashMap<>();
    }

    public TrieNode() {
        this(null);
    }

    public void put(String word, T elem) {
        char first = word.charAt(0);
        TrieNode<T> next = children.get(first);

        if (next == null) {
            next = new TrieNode<>();
            children.put(first, next);
        }

        if (word.length() != 1)
            next.put(word.substring(1), elem);
        else
            next.elem = elem;
    }

    public T get(String word) {
        TrieNode<T> next = children.get(word.charAt(0));

        if (next == null)
            return null;
        if (word.length() == 1)
            return next.elem;
        return next.get(word.substring(1));
    }

    public boolean contains(String word) {
        return this.get(word) != null;
    }

    public boolean containsPrefix(String word) {
        TrieNode<T> next = children.get(word.charAt(0));

        if (next == null)
            return false;
        if (word.length() == 1)
            return !next.children.isEmpty();
        return next.containsPrefix(word.substring(1));
    }
}
