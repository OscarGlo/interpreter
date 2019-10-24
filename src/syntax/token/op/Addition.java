package syntax.token.op;

import syntax.token.TNumber;
import syntax.token.Token;

public class Addition extends BinaryOperation<Object, Object, Object> {
    public Addition(Token[] tokens) {
        super(((o1, o2) -> {
            if (o1 instanceof String || o2 instanceof String)
                return "" + TNumber.toString(o1) + TNumber.toString(o2);
            else
                return Double.sum((Double) o1, (Double) o2);
        }), tokens);
    }
}
