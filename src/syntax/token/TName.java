package syntax.token;

public class TName extends Value<String> {
    public TName(String name) {
        super(name, String.class);
    }
}
