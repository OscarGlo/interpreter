package syntax.token;

public class TNumber extends Value<Double> {
    public TNumber(String value, int line, int pos) {
        super(Double.parseDouble(value), Double.class, line, pos);
    }
}
