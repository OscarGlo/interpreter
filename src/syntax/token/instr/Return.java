package syntax.token.instr;

import syntax.token.Token;
import syntax.token.val.Value;
import var.VariableTree;

public class Return extends Instruction {
    private Value val;

    public Return(Token[] tokens) {
        super(tokens[0]);
        checkTokenNum(tokens.length, 2);
        val = (Value) tokens[1];
    }

    @Override
    public void execute() {
        VariableTree.setInScope("return", val.getValue());
    }
}
