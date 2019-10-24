package syntax.token.instr;

import syntax.token.Token;

public abstract class Instruction extends Token {
    public abstract void execute();
}
