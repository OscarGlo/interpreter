package syntax.token.instr;

import syntax.token.Token;

public class BraInstruction extends Instruction {
    private Instruction instr;

    public BraInstruction(Token[] token) {
        checkTokenNum(token.length, 3);
        instr = (Instruction) token[1];
    }

    @Override
    public void execute() {
        instr.execute();
    }

    @Override
    public String toString() {
        return "{ " + instr.toString() + " }";
    }
}
