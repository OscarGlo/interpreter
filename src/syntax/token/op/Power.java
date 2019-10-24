package syntax.token.op;

import syntax.token.Token;

public class Power extends BinaryOperation<Double, Double, Double> {
    public Power(Token[] tokens) {
        super(Math::pow, tokens);
    }
}
