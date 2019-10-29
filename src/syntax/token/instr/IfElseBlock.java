package syntax.token.instr;

import syntax.token.Token;

public class IfElseBlock extends Instruction {
    private final IfBlock ifBlock;
    private final ElseBlock elseBlock;

    public IfElseBlock(Token[] tokens) {
        checkTokenNum(tokens.length, 2);
        ifBlock = (IfBlock) tokens[0];
        elseBlock = (ElseBlock) tokens[1];
    }

    @Override
    public void execute() {
        if (ifBlock.condition.isTruthy())
            ifBlock.instr.executeInScope();
        else
            elseBlock.executeInScope();
    }
}
