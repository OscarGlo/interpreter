package syntax.token.instr;

import syntax.token.Token;
import syntax.token.val.TName;
import syntax.token.val.Value;
import var.VariableTree;

public class Increment extends Instruction {
    private String var;

    public Increment(Token[] tokens) {
        super(tokens[1]);
        checkTokenNum(tokens.length, 2);
        var = ((TName) tokens[0]).getValueName();
    }

    @Override
    public void execute() {
        try {
            VariableTree.set(var, (Double) VariableTree.get(var) + 1);
        } catch (Throwable t) {
            throw makeStacktrace(t, "Error on increment", null);
        }
    }
}
