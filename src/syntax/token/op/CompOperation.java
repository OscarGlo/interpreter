package syntax.token.op;

import syntax.token.Token;

public class CompOperation extends BinaryOperation<Double, Double, Double> {
    public CompOperation(Token[] tokens) {
        super((a, b) -> {
            String op = tokens[1].getName();

            boolean val;
            if (op.equals("GREATER"))
                val = a > b;
            else if (op.equals("GREAT_EQ"))
                val = a >= b;
            else if (op.equals("LOWER"))
                val = a < b;
            else if (op.equals("LOW_EQ"))
                val = a <= b;
            else
                return null;

            return (val ? 1d : 0d);
        }, tokens);
    }
}
