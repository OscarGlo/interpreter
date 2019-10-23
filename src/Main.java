import syntax.token.Token;
import syntax.Tokenizer;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = Tokenizer.tokenize("var fruits = [\"Banana\", \"Orange\", \"Apple\", \"Mango\"];\n" +
                "document.getElementById(\"demo\").innerHTML = fruits;\n" +
                "\n" +
                "function myFunction() {\n" +
                "  fruits.sort();\n" +
                "  document.getElementById(\"demo\").innerHTML = fruits;\n" +
                "}");
        System.out.println(Arrays.toString(tokens.toArray()));
    }
}
