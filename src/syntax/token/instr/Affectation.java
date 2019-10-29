package syntax.token.instr;

import syntax.token.TName;
import syntax.token.Token;
import syntax.token.Value;
import var.VariableTree;

public class Affectation extends Instruction {
    private String var;
    private Value value;

    public Affectation(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        var = ((TName) tokens[0]).getValueName();
        value = (Value) tokens[2];
    }

    @Override
    public void execute() {
        VariableTree.set(var, value.getValue());
    }
}
