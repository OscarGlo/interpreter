package syntax.token.instr;

import syntax.token.Token;

public class IfElseBlock extends Instruction {
    private final IfBlock ifBlock;
    private final ElseBlock elseBlock;

    public IfElseBlock(Token[] tokens) {
        super(tokens[0]);
        ifBlock = (IfBlock) tokens[0];
        int elseIdx = tokens[1].getName().equals("SEP") ? 2 : 1;
        elseBlock = (ElseBlock) tokens[elseIdx];
    }

    @Override
    public void execute() {
        if (ifBlock.condition.isTruthy())
            try {
                ifBlock.instr.executeInScope();
            } catch (Throwable t) {
                this.stack = ifBlock.getStacktrace();
                throw t;
            }
        else
            try {
                elseBlock.executeInScope();
            } catch (Throwable t) {
                this.stack = elseBlock.getStacktrace();
                throw t;
            }
    }
}
