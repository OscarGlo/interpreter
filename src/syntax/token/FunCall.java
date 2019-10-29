package syntax.token;

import java.util.Collections;
import java.util.List;

public class FunCall extends Value<Object> {
    private final Function fun;
    private final List<Value> args;

    public FunCall(Token[] tokens) {
        fun = (Function) ((Value) tokens[0]).getValue();

        if (tokens[1] instanceof ParValue)
            args = Collections.singletonList((ParValue) tokens[1]);
        else
            args = ((ValueList) tokens[1]).asList();
    }

    @Override
    public Object getValue() {
        return fun.call((Value[]) args.toArray());
    }

    @Override
    public String toString() {
        return "FunCall";
    }
}
