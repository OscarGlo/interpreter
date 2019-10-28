package syntax.token.instr;

import syntax.token.Token;

public class ReduceInstruction extends Instruction {
    private Instruction instr;

    public ReduceInstruction(Token[] tokens) {
        for (Token tok : tokens) {
            if (tok instanceof Instruction) {
                instr = (Instruction) tok;
                return;
            }
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
}
