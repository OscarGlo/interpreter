package syntax;

import java.util.LinkedList;
import java.util.List;

public class Token {
    private String name, value;

    public Token(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static List<Token> tokenize(String program) {
        List<Token> l = new LinkedList<>();

        StringBuilder acc = new StringBuilder();

        for (int i = 0; i < program.length(); i++) {
            acc.append(program.charAt(i));
        }

        return l;
    }
}
