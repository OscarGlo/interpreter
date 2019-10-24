package syntax.token;

public abstract class Value<T> extends Token {
    private T value;
    private Class<T> type;

    public Value(T value, Class<T> type) {
        this.value = value;
        this.type = type;
    }

    public Value() {}

    public T getValue() {
        return value;
    }

    public Class<T> getType() {
        return type;
    }

    @Override
    public String toString() {
        return super.toString() + "(" + this.getValue() + ")";
    }
}
