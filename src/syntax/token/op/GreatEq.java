package syntax.token.op;

import syntax.token.Token;

public class GreatEq extends BinaryOperation<Double, Double, Double> {
    public GreatEq(Token[] tokens) {
        super((a, b) -> (double) (a >= b ? 1 : 0), tokens);
    }
}
