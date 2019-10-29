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

    public boolean isTruthy() {
        return isTruthy(getValue());
    }

    public static boolean isTruthy(Object val) {
        if (val instanceof String)
            return !val.equals("");
        else if (val instanceof Double)
            return (Double) val != 0;
        return false;
    }
}
