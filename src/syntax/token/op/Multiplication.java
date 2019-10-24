package syntax.token.op;

import syntax.token.Token;

public class Multiplication extends BinaryOperation<Double, Double, Double> {
    public Multiplication(Token[] tokens) {
        super((x, y) -> x * y, tokens);
    }
}
