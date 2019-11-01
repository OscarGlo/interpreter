package syntax.token.op;

import syntax.token.Token;

public class MulOperation extends BinaryOperation<Object, Object, Object> {
    @SuppressWarnings({"RedundantCast"})
    public MulOperation(Token[] tokens) {
        super((a, b) -> {
            String op = tokens[1].getName();

            if (op.equals("MUL")) {
                // Multiplication
                if (a instanceof Double && b instanceof Double) {
                    return (Double) a * (Double) b;
                } else if (a instanceof String) { // Swap if string is first
                    Object tmp = a;
                    a = b;
                    b = tmp;
                }

                StringBuilder str = new StringBuilder();
                for (int i = 0; i < ((Double) a).intValue(); i++)
                    str.append(b);
                return str.toString();
            } else if (op.equals("DIV")) {
                // Division
                return (Double) a / (Double) b;
            } else if (op.equals("MOD")) {
                // Modulo
                return (Double) a % (Double) b;
            }

            return null;
        }, tokens);
    }
}
