package syntax.token.val;

import syntax.token.Token;

import java.util.List;
import java.util.stream.Collectors;

public abstract class Value<T> extends Token {
    private T value;
    private Class<T> type;

    public Value(T value, Class<T> type, int line, int pos) {
        this.value = value;
        this.type = type;
        setLine(line);
        setPos(pos);
    }

    public Value(Token tok) {
        super(tok);
    }

    public Value() {
        super();
    }

    public T getValue() {
        return value;
    }

    protected void setValue(T value) {
        this.value = value;
    }

    public Class<T> getType() {
        return type;
    }

    @SuppressWarnings("unchecked")
    public static String toString(Object obj) {
        if (obj instanceof Double) {
            Double db = (Double) obj;
            if ((double) db.intValue() == db)
                return "" + db.intValue();
            else
                return db.toString();

        } else if (obj instanceof List) {
            List l = (List) obj;
            String join = (String) l.stream().map(Value::toString).collect(Collectors.joining(", "));
            return "[" + join + "]";

        } else if (obj == null) {
            return "null";
        }
        return obj.toString();
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
        return val != null;
    }
}
