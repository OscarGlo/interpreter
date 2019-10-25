package syntax.token.instr;

import syntax.token.Token;
import syntax.token.Value;

public class IfBlock extends Instruction {
    final Value condition;
    final Instruction instr;

    public IfBlock(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        condition = (Value) tokens[1];
        instr = (Instruction) tokens[2];
    }

    @Override
    public void execute() {
        if (condition.isTruthy())
            instr.execute();
    }
}
