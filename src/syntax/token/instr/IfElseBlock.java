package syntax.token.instr;

import syntax.token.Token;

public class IfElseBlock extends Instruction {
    private final IfBlock ifBlock;
    private final Instruction elseBlock;

    public IfElseBlock(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        ifBlock = (IfBlock) tokens[0];
        elseBlock = (Instruction) tokens[2];
    }

    @Override
    public void execute() {
        if (ifBlock.condition.isTruthy())
            ifBlock.instr.execute();
        else
            elseBlock.execute();
    }
}
