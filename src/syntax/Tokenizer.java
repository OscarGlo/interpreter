package syntax;

import err.CompilationError;
import util.Reader;
import util.TrieNode;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Tokenizer {
    private static TrieNode<String> tokens;

    static {
        try {
            tokens = Reader.getTrie("tokens.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static List<Token> tokenize(String program) {
        List<Token> tokenList = new LinkedList<>();

        StringBuilder acc = new StringBuilder();
        int line = 1, linesSize = 0;
        boolean inStr = false;
        int stringPos = 0;

        for (int n = 0; n < program.length(); n++) {
            char c = program.charAt(n);

            // If a linebreak is found
            if (c == '\n') {
                // Can't linebreak while defining a string
                if (inStr)
                    throw new CompilationError("Unclosed string", line, stringPos);

                line++;
                linesSize = n;

                c = ';';
            }

            acc.append(c);
            String tok = acc.toString();

            if (c == '"') { // Beginning or end of a string
                inStr = !inStr;
                if (inStr) {
                    // Save starting position
                    stringPos = n - linesSize;
                } else {
                    // Add string token and clear acc
                    tokenList.add(new Token("STRING", tok, line, stringPos));
                    acc.delete(0, acc.length());
                    continue;
                }
            }

            if (inStr)
                continue;

            // If the acc is only whitespace, ignore
            if (acc.toString().matches("\\s")) {
                acc.delete(0, 1);
                continue;
            }

            if (!tokens.containsPrefix(tok)) {
                // Error on unknown token
                if (!tokens.contains(tok))
                    throw new CompilationError("Unknown token " + tok, line, n - linesSize);

                tokenList.add(new Token(tokens.get(acc.toString()), line, n - linesSize));

                acc.delete(0, acc.length());
            }
        }

        if (acc.length() > 0)
            throw new CompilationError("Unknown token " + acc.toString(), line, program.length() - acc.length());
        else if (inStr)
            throw new CompilationError("Unclosed string", line, linesSize);

        return tokenList;
    }
}
