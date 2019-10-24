package syntax.token.op;

import syntax.token.Token;

public class Addition extends BinaryOperation<Double, Double, Double> {
    public Addition(Token[] tokens) {
        super(Double::sum, tokens);
    }
}
