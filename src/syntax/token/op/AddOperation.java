package syntax.token.op;

import syntax.token.Token;
import syntax.token.val.Value;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class AddOperation extends BinaryOperation<Object, Object, Object> {
    @SuppressWarnings({"RedundantCast", "unchecked"})
    public AddOperation(Token[] tokens) {
        super((a, b) -> {
            String op = tokens[1].getName();

            if (op.equals("PLUS")) {
                // Concatenation
                if (a instanceof String || b instanceof String)
                    return "" + Value.toString(a) + Value.toString(b);
                // Array push
                else if (a instanceof List || b instanceof List) {
                    List c;
                    if (a instanceof List) {
                        c = new LinkedList((List) a);
                        if (b instanceof List)
                            c.addAll((List) b);
                        else
                            c.add(b);
                    } else {
                        c = new LinkedList((List) b);
                        c.add(0, a);
                    }
                    return c;
                }
                // Addition
                else
                    return Double.sum((Double) a, (Double) b);

            } else if (op.equals("MINUS")) {
                // Subtraction
                return (Double) a - (Double) b;
            }

            return null;
        }, tokens);
    }
}
