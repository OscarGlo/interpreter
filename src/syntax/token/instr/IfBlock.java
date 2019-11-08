package syntax.token.instr;

import syntax.token.Token;
import syntax.token.val.Value;

public class IfBlock extends Instruction {
    final Value condition;
    final Instruction instr;

    public IfBlock(Token[] tokens) {
        super(tokens[0]);
        checkTokenNum(tokens.length, 3);
        condition = (Value) tokens[1];
        instr = (Instruction) tokens[2];
    }

    @Override
    public void execute() {
        if (condition.isTruthy())
            try {
                instr.executeInScope();
            } catch (Throwable t) {
                throw makeStacktrace(t,"Error in if", instr.getStacktrace());
            }
    }
}
