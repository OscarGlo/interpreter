package syntax.token.instr;

import err.Stacktrace;
import syntax.token.Token;

public class ElseBlock extends ReduceInstruction {
    public ElseBlock(Token[] tokens) {
        super(tokens);
    }

    @Override
    public void execute() {
        try {
            instr.execute();
        } catch (Throwable t) {
            throw makeStacktrace(t, "Error in else", instr.getStacktrace());
        }
    }

    @Override
    public String toString() {
        return "ElseBlock";
    }
}
