package syntax.token.val;

import syntax.token.Token;
import syntax.token.TokenList;

import java.util.Collections;
import java.util.List;

public class FunCall extends Value<Object> {
    private final TName funName;
    private final List<Value> args;

    public FunCall(Token[] tokens) {
        super(tokens[0]);

        funName = (TName) tokens[0];

        if (tokens[1] instanceof ParValue)
            args = Collections.singletonList((ParValue) tokens[1]);
        else if (tokens[1].getName().equals("LPAR"))
            args = Collections.emptyList();
        else
            args = ((TokenList<Value>) tokens[1]).asList();
    }

    @Override
    public Object getValue() {
        try {
            Function fun = (Function) funName.getValue();
            return fun.call(args.toArray(new Value[]{}));
        } catch (Throwable t) {
            Value errorVal = args.stream().reduce((v1, v2) -> (v1.getStacktrace() != null ? v1 : v2)).get();
            throw makeStacktrace(t, "Function call error", errorVal.getStacktrace());
        }
    }

    @Override
    public String toString() {
        return "FunCall";
    }
}
