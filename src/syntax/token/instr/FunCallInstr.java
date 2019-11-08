package syntax.token.instr;

import err.Stacktrace;
import syntax.token.val.FunCall;
import syntax.token.Token;

public class FunCallInstr extends Instruction {
    private FunCall call;

    public FunCallInstr(Token[] tokens) {
        super(tokens[0]);
        checkTokenNum(tokens.length, 1);
        call = (FunCall) tokens[0];
    }

    @Override
    public void execute() {
        call.getValue();
    }

    @Override
    public Stacktrace getStacktrace() {
        return call.getStacktrace();
    }
}
