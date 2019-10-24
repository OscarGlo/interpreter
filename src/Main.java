import syntax.TreeBuilder;
import syntax.token.Token;
import syntax.Tokenizer;
import syntax.token.instr.Instruction;
import var.VariableTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String code = new Scanner(new File("code.txt")).useDelimiter("\\A").next();

        List<Token> tokens = Tokenizer.tokenize(code);
        TreeBuilder.build(tokens);
        ((Instruction) tokens.get(0)).execute();

        System.out.println(VariableTree.get("y"));
    }
}
