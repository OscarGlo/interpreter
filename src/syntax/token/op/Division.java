package syntax.token.op;

import syntax.token.Token;

public class Division extends BinaryOperation<Double, Double, Double> {
    public Division(Token[] tokens) {
        super((a, b) -> a / b, tokens);
    }
}
