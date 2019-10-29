package syntax.token.op;

import syntax.token.TNumber;
import syntax.token.Token;

public class NotEq extends BinaryOperation<Object, Object, Double> {
    public NotEq(Token[] tokens) {
        super((a, b) -> (double) (!a.equals(b) && !TNumber.toString(a).equals(TNumber.toString(b)) ? 1 : 0), tokens);
    }
}
