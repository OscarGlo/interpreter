package syntax.token.instr;

import err.Stacktrace;
import syntax.token.Token;

public class ReduceInstruction extends Instruction {
    Instruction instr;

    public ReduceInstruction(Token[] tokens) {
        super(tokens[0]);
        for (Token tok : tokens)
            if (tok instanceof Instruction) {
                instr = (Instruction) tok;
                return;
            }
        throw new RuntimeException("No instruction found in parameters for ReduceInstruction");
    }

    @Override
    public void execute() {
        instr.execute();
    }

    @Override
    public String toString() {
        return "-" + instr.toString();
    }

    @Override
    public Stacktrace getStacktrace() {
        return instr.getStacktrace();
    }
}
