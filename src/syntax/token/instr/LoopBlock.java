package syntax.token.instr;

import syntax.token.Token;
import syntax.token.val.Value;

public class LoopBlock extends Instruction {
    final Value condition;
    final Instruction instr;

    public LoopBlock(Token[] tokens) {
        super(tokens[0]);
        checkTokenNum(tokens.length, 4);
        condition = (Value) tokens[1];
        instr = (Instruction) tokens[3];
    }

    @Override
    public void execute() {
        try {
            while (condition.isTruthy())
                instr.executeInScope();
        } catch (Throwable t) {
            throw makeStacktrace(t, "Error in loop", instr.getStacktrace());
        }
    }
}
