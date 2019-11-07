package syntax.token;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class TArray extends Value<List> {
    private List<Value> values;

    @SuppressWarnings("unchecked")
    public TArray(Token[] tokens) {
        if (tokens.length == 2) {
            this.values = Collections.emptyList();
        } else if (tokens[1] instanceof Value) {
            this.values = Collections.singletonList((Value) tokens[1]);
        } else {
            this.values = ((TokenList<Value>) tokens[1]).asList();
        }
    }

    @Override
    public List getValue() {
        return values.stream().map(Value::getValue).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "TArray[" + values.size() + "]";
    }
}
