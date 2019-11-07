package syntax.token;

import java.util.List;

public class ArrayAccess extends Value<Object> {
    private Value<List> arr;
    private Value<Double> index;

    @SuppressWarnings("unchecked")
    public ArrayAccess(Token[] tokens) {
        checkTokenNum(tokens.length, 4);
        arr = (Value<List>) tokens[0];
        index = (Value<Double>) tokens[2];
    }

    @Override
    public Object getValue() {
        return arr.getValue().get(index.getValue().intValue());
    }

    @Override
    public String toString() {
        return "ArrayAccess";
    }
}
