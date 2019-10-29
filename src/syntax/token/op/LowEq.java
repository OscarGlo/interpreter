package syntax.token.op;

import syntax.token.Token;

public class LowEq extends BinaryOperation<Double, Double, Double> {
    public LowEq(Token[] tokens) {
        super((a, b) -> (double) (a <= b ? 1 : 0), tokens);
    }
}
