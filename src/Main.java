import err.InterpreterError;
import syntax.Tokenizer;
import syntax.TreeBuilder;
import syntax.token.Token;
import syntax.token.instr.Instruction;
import var.VariableTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String code = new Scanner(new File("code.txt")).useDelimiter("\\A").next();

        long start = System.currentTimeMillis();

        List<Token> tokens = Tokenizer.tokenize(code);
        TreeBuilder.build(tokens);

        if (tokens.size() > 1)
            throw new InterpreterError("Could not interpret whole program");

        System.out.println("Parse time = " + (System.currentTimeMillis() - start) + "ms");

        ((Instruction) tokens.get(0)).execute();

        System.out.println(VariableTree.get("x"));
    }
}
