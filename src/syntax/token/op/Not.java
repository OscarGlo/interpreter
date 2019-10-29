package syntax.token.op;

import syntax.token.Token;
import syntax.token.Value;

public class Not extends UnaryOperation<Object, Double> {
    public Not(Token[] tokens) {
        super((a) -> (double) (Value.isTruthy(a) ? 0 : 1), tokens);
    }
}
