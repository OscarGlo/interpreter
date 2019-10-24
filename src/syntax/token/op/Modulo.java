package syntax.token.op;

import syntax.token.Token;

public class Modulo extends BinaryOperation<Double, Double, Double> {
    public Modulo(Token[] tokens) {
        super((a, b) -> a % b, tokens);
    }
}
