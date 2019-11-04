package syntax.token.instr;

import err.Stacktrace;
import syntax.token.Token;

public class BraInstruction extends Instruction {
    private Instruction instr;

    public BraInstruction(Token[] tokens) {
        super(tokens[1]);
        checkTokenNum(tokens.length, 3);
        instr = (Instruction) tokens[1];
    }

    @Override
    public void execute() {
        instr.execute();
    }

    @Override
    public Stacktrace getStacktrace() {
        return instr.getStacktrace();
    }

    @Override
    public String toString() {
        return "{ " + instr.toString() + " }";
    }
}
