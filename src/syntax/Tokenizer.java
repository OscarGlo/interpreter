package syntax;

import err.InterpreterError;
import syntax.token.*;
import syntax.token.val.TName;
import syntax.token.val.TNumber;
import syntax.token.val.TString;
import util.Reader;
import util.TrieNode;

import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;

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

        String acc = "";
        int line = 1, pos = 0;
        boolean inStr = false, inComm = false, oneHash = false, inMultiComm = false, inNum = false, inName = false, escape = false;
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
            } else if (c == '#') {
                // If there was a hash before
                if (oneHash) {
                    // Invert multi comment status
                    inMultiComm = !inMultiComm;
                    inComm = false;
                    continue;
                } else if (!inComm || inMultiComm) {
                    if (!inMultiComm)
                        inComm = true;
                    oneHash = true;
                    continue;
                }
            } else if (inStr && c == '\\' && !escape) {
                escape = true;
                continue;
            }

            oneHash = false;

            // Ignore rest if in comment
            if (inComm || inMultiComm) {
                continue;
            }

            if (inNum) {
                // Multiple decimal points
                if (c == '.' && acc.contains("."))
                    throw new InterpreterError("Unexpected token .", line, pos);

                // End of number
                if (!cStr.matches("[0-9.]")) {
                    inNum = false;
                    if (!acc.equals(".")) {
                        tokenList.add(new TNumber(acc, line, pos));
                        acc = "";
                    }
                }
            }

            // End of name
            if (inName && !cStr.matches("\\w")) {
                inName = false;
                tokenList.add(new TName(acc, line, pos));
                acc = "";
            }

            if (acc.length() > 0 && tokens.contains(acc) && !tokens.contains(acc + c) && !tokens.containsPrefix(acc + c)) {
                tokenList.add(new Token(tokens.get(acc), line, pos-1));
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
            else if (c == '"') {
                if (!escape) {
                    inStr = !inStr;
                    if (inStr) {
                        // Save starting position
                        stringPos = pos;
                    } else {
                        // Add string token and clear acc
                        tokenList.add(new TString(acc.substring(1, acc.length() - 1), line, pos));
                        acc = "";
                        continue;
                    }
                } else {
                    escape = false;
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
                    tokenList.add(new Token(tokens.get(acc), line, pos));
                    acc = "";
                    inName = false;
                } else if (!tokens.contains(acc) && !inName)
                    // Error on unknown token
                    throw new InterpreterError("Unknown token " + acc, line, pos);
        }

        if (inNum) {
            tokenList.add(new TNumber(acc, line, pos));
            acc = "";
        } else if (inStr) {
            tokenList.add(new TString(acc, line, pos));
            acc = "";
        } else if (inName) {
            tokenList.add(new TName(acc, line, pos));
            acc = "";
        }

        if (acc.length() > 0)
            throw new InterpreterError("Unknown token " + acc, line, program.length() - acc.length());
        else if (inStr)
            throw new InterpreterError("Unclosed string", line, pos - acc.length());

        return tokenList;
    }
}
