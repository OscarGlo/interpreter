package syntax.token.op;

import syntax.token.val.TNumber;
import syntax.token.Token;

public class EqOperation extends BinaryOperation<Object, Object, Double> {
    public EqOperation(Token[] tokens) {
        super((a, b) -> {
            boolean eq = a.equals(b) || TNumber.toString(a).equals(TNumber.toString(b));

            String op = tokens[1].getName();

            if (op.equals("NOT_EQ"))
                eq = !eq;
            else if (!op.equals("EQUAL"))
                return null;

            return (eq ? 1d : 0d);
        }, tokens);
    }
}