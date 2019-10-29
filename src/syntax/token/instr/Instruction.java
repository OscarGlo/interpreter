package syntax.token.instr;

import syntax.token.Token;
import var.VariableTree;

public abstract class Instruction extends Token {
    public abstract void execute();

    public void executeInScope() {
        VariableTree.addScope();
        execute();
        VariableTree.delScope();
    }
}
