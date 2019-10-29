package syntax.token.op;

import syntax.token.Token;
import syntax.token.Value;

public class And extends BinaryOperation<Object, Object, Double> {
    public And(Token[] tokens) {
        super((a, b) -> (double) (Value.isTruthy(a) && Value.isTruthy(b) ? 1 : 0), tokens);
    }
}
