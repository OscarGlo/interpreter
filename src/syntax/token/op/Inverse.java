package syntax.token.op;

import syntax.token.Token;

public class Inverse extends UnaryOperation<Double, Double> {
    public Inverse(Token[] tokens) {
        super(a -> -a, tokens);
    }
}
