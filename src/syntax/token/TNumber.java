package syntax.token;

public class TNumber extends Value<Double> {
    public TNumber(String value) {
        super(Double.parseDouble(value), Double.class);
    }

    public static String toString(Object obj) {
        if (obj instanceof Double) {
            Double db = (Double) obj;
            if ((double) db.intValue() == db)
                return "" + db.intValue();
            else
                return db.toString();
        } else {
            return obj.toString();
        }
    }
}
