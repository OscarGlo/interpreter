package syntax.token.op;

import syntax.token.Token;

public class Square extends UnaryOperation<Double, Double> {
    public Square(Token[] tokens) {
        super(a -> a * a, true, tokens);
    }
}
