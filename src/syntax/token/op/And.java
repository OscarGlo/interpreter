package syntax.token.op;

import syntax.token.Token;
import syntax.token.val.Value;

public class And extends BinaryOperation<Object, Object, Double> {
    public And(Token[] tokens) {
        super((a, b) -> (Value.isTruthy(a) && Value.isTruthy(b) ? 1d : 0d), tokens);
    }
}
