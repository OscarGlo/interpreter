package syntax.token.op;

import syntax.token.Token;

public class Greater extends BinaryOperation<Double, Double, Double> {
    public Greater(Token[] tokens) {
        super((a, b) -> (double) (a > b ? 1 : 0), tokens);
    }
}
