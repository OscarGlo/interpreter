package syntax.token.op;

import syntax.token.TNumber;
import syntax.token.Token;

public class EqOperation extends BinaryOperation<Object, Object, Double> {
    public EqOperation(Token[] tokens) {
        super((a, b) -> {
            boolean eq = a.equals(b) || TNumber.toString(a).equals(TNumber.toString(b));

            if (tokens[1].getName().equals("NOT_EQ"))
                eq = !eq;

            return (double) (eq ? 1 : 0);
        }, tokens);
    }
}