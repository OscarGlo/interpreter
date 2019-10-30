package main;

import err.InterpreterError;
import syntax.Tokenizer;
import syntax.TreeBuilder;
import syntax.token.Token;
import syntax.token.instr.Instruction;
import util.Reader;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static Token evaluate(String code, boolean debug) {
        List<Token> tokens = Tokenizer.tokenize(code);
        TreeBuilder.build(tokens, debug);

        if (tokens.size() > 1)
            throw new InterpreterError("Could not interpret whole program");

        return tokens.get(0);
    }

    public static void main(String[] args) throws FileNotFoundException {
        String code = Reader.getString("code.txt");

        long start = System.currentTimeMillis();
        Instruction instr = (Instruction) evaluate(code, true);
        System.out.println("Parse time = " + (System.currentTimeMillis() - start) + "ms\n-----");

        instr.execute();
    }
}
