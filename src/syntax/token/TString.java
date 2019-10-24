package syntax.token;

public class TString extends Value<String> {
    public TString(String value) {
        super(value, String.class);
    }
}
