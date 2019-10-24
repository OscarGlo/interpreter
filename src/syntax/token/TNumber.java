package syntax.token;

public class TNumber extends Value<Double> {
    public TNumber(String value) {
        super(Double.parseDouble(value), Double.class);
    }
}
