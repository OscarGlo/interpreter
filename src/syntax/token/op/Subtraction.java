package syntax.token.op;

import syntax.token.Token;

public class Subtraction extends BinaryOperation<Double, Double, Double> {
    public Subtraction(Token[] tokens) {
        super((a, b) -> a - b, tokens);
    }
}
