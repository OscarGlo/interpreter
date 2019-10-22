import syntax.Token;
import syntax.Tokenizer;
import util.TrieNode;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = Tokenizer.tokenize("if (true==true) {\n  efalse\n}");
        System.out.println(Arrays.toString(tokens.toArray()));
    }
}
