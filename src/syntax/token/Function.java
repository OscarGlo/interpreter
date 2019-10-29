package syntax.token;

import syntax.token.instr.Instruction;
import var.VariableTree;

import java.util.Collections;
import java.util.List;

public class Function extends Value {
    private List<String> params;
    private Instruction instr;

    public Function(Token[] tokens) {
        if (tokens[1] instanceof ParValue) {
            Token tok = ((ParValue) tokens[1]).getToken();
            String valName = ((TName) tok).getValueName();
            params = Collections.singletonList(valName);
        } else
            params = ((NameList) tokens[1]).asList();
        instr = (Instruction) tokens[2];
    }

    public Function() {}

    public Object call(Value[] values) {
        VariableTree.addScope();
        for (int i = 0; i < params.size() && i < values.length; i++) {
            // FIXME: Parameters with the same name as outer vars are overridden
            VariableTree.set(params.get(i), values[i]);
        }
        instr.execute();
        Object ret = VariableTree.get("return");
        VariableTree.delScope();
        return ret;
    }
}
