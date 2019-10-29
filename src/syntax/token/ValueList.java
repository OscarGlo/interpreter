package syntax.token;

import java.util.LinkedList;
import java.util.List;

public class ValueList extends Token {
    private Value param;
    private ValueList next;

    public ValueList(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        param = (Value) tokens[0];

        if (tokens[2] instanceof Value)
            next = new ValueList((Value) tokens[2]);
        else
            next = (ValueList) tokens[2];
    }

    private ValueList(Value param) {
        this.param = param;
    }

    public List<Value> asList() {
        return asList(new LinkedList<>());
    }

    private List<Value> asList(List<Value> l) {
        l.add(this.param);
        if (next != null)
            return next.asList(l);
        return l;
    }
}
