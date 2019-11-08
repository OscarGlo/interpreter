package syntax.token.val;

import syntax.token.Token;
import syntax.token.TokenList;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FunCall extends Value<Object> {
    private final Value<Function> funVal;
    private final List<Value> args;

    @SuppressWarnings("unchecked")
    public FunCall(Token[] tokens) {
        super(tokens[0]);

        funVal = (Value<Function>) tokens[0];

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
            Function fun = funVal.getValue();
            return fun.call(args.toArray(new Value[]{}));
        } catch (Throwable t) {
            Optional<Value> errorVal = args.stream().reduce((v1, v2) -> (v1.getStacktrace() != null ? v1 : v2));
            throw makeStacktrace(t, "Function call error", (errorVal.map(Token::getStacktrace).orElse(null)));
        }
    }

    @Override
    public String toString() {
        return "FunCall";
    }
}
