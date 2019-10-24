import syntax.TreeBuilder;
import syntax.token.Token;
import syntax.Tokenizer;
import syntax.token.instr.Instruction;
import var.VariableTree;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Token> tokens = Tokenizer.tokenize("x = 2 * 3.5; y = (2.0 * 5) ^ 3;");
        TreeBuilder.build(tokens);

        ((Instruction) tokens.get(0)).execute();
        System.out.println(VariableTree.get("x"));
        System.out.println(VariableTree.get("y"));
    }
}
