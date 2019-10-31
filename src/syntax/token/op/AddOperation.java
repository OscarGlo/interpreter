package syntax.token.op;

import syntax.token.TNumber;
import syntax.token.Token;

public class AddOperation extends BinaryOperation<Object, Object, Object> {
    @SuppressWarnings("RedundantCast")
    public AddOperation(Token[] tokens) {
        super((a, b) -> {
            String op = tokens[1].getName();

            if (op.equals("ADD")) {
                // Addition
                if (a instanceof String || b instanceof String)
                    return "" + TNumber.toString(a) + TNumber.toString(b);
                else
                    return Double.sum((Double) a, (Double) b);

            } else {
                // Subtraction
                return (Double) a - (Double) b;
            }
        }, tokens);
    }
}
