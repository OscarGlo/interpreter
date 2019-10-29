package syntax.token.op;

import syntax.token.Token;
import syntax.token.Value;

public class Or extends BinaryOperation<Object, Object, Object> {
    public Or(Token[] tokens) {
        super((a, b) -> (Value.isTruthy(a) ? a : b), tokens);
    }
}
