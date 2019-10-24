package syntax.token.instr;

import syntax.token.Token;

public class InstructionList extends Instruction {
    private Instruction i1, i2;

    public InstructionList(Token[] tokens) {
        checkTokenNum(tokens.length, 2);
        i1 = (Instruction) tokens[0];
        i2 = (Instruction) tokens[1];
    }

    @Override
    public void execute() {
        i1.execute();
        i2.execute();
    }
}
