package syntax.token.instr;

import syntax.token.Token;

public class InstructionList extends Instruction {
    private Instruction i1, i2;

    public InstructionList(Token[] tokens) {
        i1 = (Instruction) tokens[0];
        if (tokens.length == 2)
            i2 = (Instruction) tokens[1];
        else
            i2 = (Instruction) tokens[2];
    }

    @Override
    public void execute() {
        i1.execute();
        i2.execute();
    }
}
