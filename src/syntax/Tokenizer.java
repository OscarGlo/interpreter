package syntax;

import err.InterpreterError;
import syntax.token.TName;
import syntax.token.TNumber;
import syntax.token.TString;
import syntax.token.Token;
import util.Reader;
import util.TrieNode;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

public class Tokenizer {
    private final static char
            STR_DELIM = '"',
            COMM_DELIM = '#';

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

        // FIXME: Temporary bugfix, last token sometimes break
        program += ";";

        String acc = "";
        int line = 1, pos = 0;
        boolean inStr = false, inComm = false, inNum = false, inName = false;
        int stringPos = 0;

        // Loop through every character in the program
        for (int n = 0; n < program.length(); n++) {
            char c = program.charAt(n);
            String cStr = "" + c;

            pos++;

            // If a linebreak is found
            if (c == '\n') {
                // Can't linebreak while defining a string
                if (inStr)
                    throw new InterpreterError("Unclosed string", line, stringPos);

                inComm = false;

                // Reset position and add line number
                line++;
                pos = 0;

                c = ';';
            } else if (c == COMM_DELIM)
                inComm = true;

            // Ignore rest if in comment
            if (inComm)
                continue;

            if (inNum) {
                // Multiple decimal points
                if (c == '.' && acc.contains("."))
                    throw new InterpreterError("Unexpected token .", line, pos);

                // End of number
                if (!cStr.matches("[0-9.]")) {
                    if (acc.equals(".")) {
                        inNum = false;
                    } else {
                        inNum = false;
                        tokenList.add(new TNumber(acc));
                        acc = "";
                    }
                }
            }

            // End of name
            if (inName && !cStr.matches("\\w")) {
                inName = false;
                tokenList.add(new TName(acc));
                acc = "";
            }

            if (acc.length() > 0 && tokens.contains(acc) && !tokens.contains(acc + c) && !tokens.containsPrefix(acc + c)) {
                tokenList.add(new Token(tokens.get(acc)));
                acc = "";
                inName = false;
            }

            // CHARACTER APPENDED
            acc += c;

            // Number
            if (!inNum && acc.matches("[0-9.]"))
                inNum = true;
            // Name
            else if (!inName && acc.matches("\\w"))
                inName = true;
            // Beginning or end of a string
            else if (c == STR_DELIM) {
                inStr = !inStr;
                if (inStr) {
                    // Save starting position
                    stringPos = pos;
                } else {
                    // Add string token and clear acc
                    tokenList.add(new TString(acc.substring(1, acc.length() - 1)));
                    acc = "";
                    continue;
                }
            }

            // Ignore token parsing
            if (inStr || inNum)
                continue;

            // If the acc is only whitespace, ignore
            if (acc.matches("\\s")) {
                acc = "";
                continue;
            }

            if (!tokens.containsPrefix(acc))
                if (tokens.contains(acc)) {
                    tokenList.add(new Token(tokens.get(acc)));
                    acc = "";
                    inName = false;
                } else if (!tokens.contains(acc) && !inName)
                    // Error on unknown token
                    throw new InterpreterError("Unknown token " + acc, line, pos);
        }

        if (acc.length() > 0)
            throw new InterpreterError("Unknown token " + acc, line, program.length() - acc.length());
        else if (inStr)
            throw new InterpreterError("Unclosed string", line, pos);

        return tokenList;
    }
}
