package syntax.token.op;

import syntax.token.Token;

public class CompOperation extends BinaryOperation<Double, Double, Double> {
    public CompOperation(Token[] tokens) {
        super((a, b) -> {
            String op = tokens[1].getName();

            boolean val;
            if ("GREATER".equals(op))
                val = a > b;
            else if ("GREAT_EQ".equals(op))
                val = a >= b;
            else if ("LOWER".equals(op))
                val = a < b;
            else
                val = a <= b;

            return (double) (val ? 1 : 0);
        }, tokens);
    }
}
