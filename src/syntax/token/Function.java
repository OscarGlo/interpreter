package syntax.token;

import syntax.token.instr.Instruction;
import var.VariableTree;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Function extends Value<Function> {
    private List<String> params;
    private Instruction instr;

    public Function(Token[] tokens) {
        if (tokens[1] instanceof ParValue) {
            Token tok = ((ParValue) tokens[1]).getToken();
            String valName = ((TName) tok).getValueName();
            params = Collections.singletonList(valName);
        } else if (tokens[1].getName().equals("LPAR"))
            params = Collections.emptyList();
        else
            params = ((TokenList<TName>) tokens[1]).asList()
                    .stream().map(TName::getValueName).collect(Collectors.toList());

        instr = (Instruction) tokens[tokens.length - 1];
    }

    public Function() {}

    @Override
    public Function getValue() {
        return this;
    }

    public Object call(Value[] values) {
        VariableTree.addScope();
        for (int i = 0; i < params.size() && i < values.length; i++) {
            VariableTree.setInScope(params.get(i), values[i].getValue());
        }
        instr.execute();
        Object ret = VariableTree.get("return");
        VariableTree.delScope();
        return ret;
    }

    @Override
    public String toString() {
        return "Function";
    }
}
