package syntax.token.op;

import syntax.token.Token;
import syntax.token.Value;

public class AddOperation extends BinaryOperation<Object, Object, Object> {
    @SuppressWarnings("RedundantCast")
    public AddOperation(Token[] tokens) {
        super((a, b) -> {
            String op = tokens[1].getName();

            if (op.equals("PLUS")) {
                // Addition
                if (a instanceof String || b instanceof String)
                    return "" + Value.toString(a) + Value.toString(b);
                else
                    return Double.sum((Double) a, (Double) b);

            } else if (op.equals("MINUS")) {
                // Subtraction
                return (Double) a - (Double) b;
            }

            return null;
        }, tokens);
    }
}
