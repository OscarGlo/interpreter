package syntax.token.val;

import syntax.token.Token;
import syntax.token.val.Value;

import java.util.List;

public class ArrayAccess extends Value<Object> {
    private Value<List> arr;
    private Value<List> indexes;

    @SuppressWarnings("unchecked")
    public ArrayAccess(Token[] tokens) {
        checkTokenNum(tokens.length, 2);
        arr = (Value<List>) tokens[0];
        indexes = (Value<List>) tokens[1];
    }

    @Override
    public Object getValue() {
        try {
            List arrList = arr.getValue();
            List idxs = indexes.getValue();
            for (int i = 0; i < idxs.size(); i++) {
                Double index = (Double) idxs.get(i);
                Object next = arrList.get(index.intValue());
                if (i == idxs.size() - 1)
                    return next;
                else
                    arrList = (List) next;
            }
            return null;
        } catch (Throwable t) {
            throw makeStacktrace(t, "Invalid array access", arr.getStacktrace());
        }
    }

    @Override
    public String toString() {
        return "ArrayAccess";
    }
}
