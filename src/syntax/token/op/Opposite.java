package syntax.token.op;

import syntax.token.Token;

public class Opposite extends UnaryOperation<Double, Double> {
    public Opposite(Token[] tokens) {
        super(a -> -a, tokens);
    }
}
