package syntax.token.op;

import syntax.token.Token;
import syntax.token.val.Value;

public class Not extends UnaryOperation<Object, Double> {
    public Not(Token[] tokens) {
        super((a) -> (Value.isTruthy(a) ? 0d : 1d), tokens);
    }
}
