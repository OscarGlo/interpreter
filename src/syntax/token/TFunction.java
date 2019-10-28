package syntax.token;

import syntax.token.instr.Instruction;

import java.util.LinkedList;
import java.util.List;

public class TFunction extends Value<Instruction> {
    private List<String> params;

    public TFunction(Token[] tokens) {
        int i = 2;
        params = new LinkedList<>();
        while (!tokens[i].getName().equals("RPAR")) {
            params.add(((TName) tokens[i]).);
            i += 2;
        }
    }
}
