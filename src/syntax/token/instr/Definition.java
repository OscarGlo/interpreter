package syntax.token.instr;

import syntax.token.Token;
import var.VariableTree;

import java.util.Arrays;

public class Definition extends Affectation {
    public Definition(Token[] tokens) {
        super(Arrays.copyOfRange(tokens, 1, 4));
    }

    @Override
    public void execute() {
        VariableTree.setInScope(var, value.getValue());
    }
}
