package syntax.token;

public class TString extends Value<String> {
    public TString(String value, int line, int pos) {
        super(value, String.class, line, pos);
    }
}
