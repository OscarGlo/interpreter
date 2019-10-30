package syntax.token.op;

import syntax.token.Token;

public class Nullity extends UnaryOperation<Object, Double> {
    public Nullity(Token[] tokens) {
        super(a -> (double) (a != null ? 1 : 0), tokens);
    }
}
