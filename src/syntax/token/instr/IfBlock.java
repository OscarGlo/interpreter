package syntax.token.instr;

import syntax.token.Token;
import syntax.token.Value;

public class IfBlock extends Instruction {
    private final Value condition;
    private final Instruction instr;

    public IfBlock(Token[] tokens) {
        checkTokenNum(tokens.length, 5);
        condition = (Value) tokens[1];
        instr = (Instruction) tokens[3];
    }

    @Override
    public void execute() {
        if (condition.isTruthy())
            instr.execute();
    }
}
