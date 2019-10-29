package syntax.token.instr;

import syntax.token.Token;
import syntax.token.Value;

public class LoopBlock extends Instruction {
    final Value condition;
    final Instruction instr;

    public LoopBlock(Token[] tokens) {
        checkTokenNum(tokens.length, 4);
        condition = (Value) tokens[1];
        instr = (Instruction) tokens[3];
    }

    @Override
    public void execute() {
        while (condition.isTruthy())
            instr.executeInScope();
    }
}
