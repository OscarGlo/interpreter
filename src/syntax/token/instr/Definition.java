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
        try {
            VariableTree.setInScope(var, value.getValue());
        } catch (Throwable t) {
            throw makeStacktrace(t, "Error on definition", value.getStacktrace());
        }
    }
}
