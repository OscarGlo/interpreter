package syntax.token.op;

import syntax.token.Token;

public class Lower extends BinaryOperation<Double, Double, Double> {
    public Lower(Token[] tokens) {
        super((a, b) -> (double) (a < b ? 1 : 0), tokens);
    }
}
