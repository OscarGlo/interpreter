package syntax.token.op;

import syntax.token.Token;

public class Multiplication extends BinaryOperation<Object, Object, Object> {
    public Multiplication(Token[] tokens) {
        super((x, y) -> {
            if (x instanceof Double && y instanceof Double) {
                return (Double) x * (Double) y;
            } else if (x instanceof String) { // Swap if string is first
                Object tmp = x;
                x = y;
                y = tmp;
            }

            StringBuilder str = new StringBuilder();
            for (int i = 0; i < ((Double) x).intValue(); i++)
                str.append(y);
            return str.toString();
        }, tokens);
    }
}
