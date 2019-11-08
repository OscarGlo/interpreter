package syntax.token.instr;

import syntax.token.val.TName;
import syntax.token.Token;
import syntax.token.val.Value;
import var.VariableTree;

public class Affectation extends Instruction {
    String var;
    Value value;

    public Affectation(Token[] tokens) {
        super(tokens[1]);
        checkTokenNum(tokens.length, 3);
        var = ((TName) tokens[0]).getValueName();
        value = (Value) tokens[2];
    }

    @Override
    public void execute() {
        try {
            VariableTree.set(var, value.getValue());
        } catch (Throwable t) {
            throw makeStacktrace(t, "Error on affectation", value.getStacktrace());
        }
    }
}
