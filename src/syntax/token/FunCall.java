package syntax.token;

import java.util.Collections;
import java.util.List;

public class FunCall extends Value<Object> {
    private final TName funName;
    private final List<Value> args;

    public FunCall(Token[] tokens) {
        funName = (TName) tokens[0];

        if (tokens[1] instanceof ParValue)
            args = Collections.singletonList((ParValue) tokens[1]);
        else if (tokens[1].getName().equals("LPAR"))
            args = Collections.emptyList();
        else
            args = ((ValueList) tokens[1]).asList();
    }

    @Override
    public Object getValue() {
        Function fun = (Function) funName.getValue();
        return fun.call(args.toArray(new Value[] {}));
    }

    @Override
    public String toString() {
        return "FunCall";
    }
}
