package syntax.token;

public class ParValue<T> extends Value<T> {
    private final Value<T> vTok;

    @SuppressWarnings("unchecked")
    public ParValue(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        vTok = (Value<T>) tokens[1];
    }

    @Override
    public T getValue() {
        return vTok.getValue();
    }

    @Override
    public Class<T> getType() {
        return vTok.getType();
    }

    public Value<T> getToken() {
        return vTok;
    }

    @Override
    public String toString() {
        return "( " + vTok.toString() + " )";
    }
}
