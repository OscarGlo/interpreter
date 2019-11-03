package syntax.token;

public class TNumber extends Value<Double> {
    public TNumber(String value, int line, int pos) {
        super(Double.parseDouble(value), Double.class, line, pos);
    }

    public static String toString(Object obj) {
        if (obj instanceof Double) {
            Double db = (Double) obj;
            if ((double) db.intValue() == db)
                return "" + db.intValue();
            else
                return db.toString();
        } else if (obj != null) {
            return obj.toString();
        } else {
            return "null";
        }
    }
}
