package syntax.token.op;

import syntax.token.Token;

public class Nullity extends UnaryOperation<Object, Double> {
    public Nullity(Token[] tokens) {
        super(a -> (a != null ? 1d : 0d), tokens);
    }
}
