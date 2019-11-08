package syntax.token.val;

import syntax.token.Token;
import syntax.token.TokenList;
import syntax.token.instr.Instruction;
import var.VariableTree;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Function extends Value<Function> {
    private List<String> params;
    private Instruction instr;
    private VariableTree context;

    public Function(Token[] tokens) {
        super(tokens[0]);

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

    public Function() {
        super();
    }

    @Override
    public Function getValue() {
        if (context == null)
            context = VariableTree.instance();
        return this;
    }

    public Object call(Value[] values) {
        // Get values before loading context
        List<Object> vals = Arrays.asList(values).stream().map(Value::getValue).collect(Collectors.toList());

        VariableTree.addInstance(context);
        VariableTree.addScope();
        for (int i = 0; i < params.size() && i < vals.size(); i++) {
            VariableTree.setInScope(params.get(i), vals.get(i));
        }
        instr.execute();
        Object ret = VariableTree.get("return");
        VariableTree.delScope();
        VariableTree.delInstance();
        return ret;
    }

    @Override
    public String toString() {
        return "Function[" + params.size() + ']';
    }
}
