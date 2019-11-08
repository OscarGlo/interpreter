package syntax.token.val;

import syntax.token.Token;

public class TNumber extends Value<Double> {
    public TNumber(String value, int line, int pos) {
        super(Double.parseDouble(value), Double.class, line, pos);
    }

    public TNumber(Token[] tokens) {
        super(tokens[0]);
        checkTokenNum(tokens.length, 1);
        setValue(tokens[0].getName().equals("TRUE") ? 1d : 0d);
    }
}
