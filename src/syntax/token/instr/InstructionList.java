package syntax.token.instr;

import syntax.token.Token;

public class InstructionList extends Instruction {
    private Instruction i1, i2;

    public InstructionList(Token[] tokens) {
        super(tokens[0]);
        i1 = (Instruction) tokens[0];
        if (tokens.length == 2)
            i2 = (Instruction) tokens[1];
        else
            i2 = (Instruction) tokens[2];
    }

    @Override
    public void execute() {
        try {
            i1.execute();
        } catch (Throwable t) {
            this.stack = i1.getStacktrace();
            throw t;
        }
        try {
            i2.execute();
        } catch (Throwable t) {
            this.stack = i2.getStacktrace();
            throw t;
        }
    }
}
