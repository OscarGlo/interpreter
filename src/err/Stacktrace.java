package err;

public class Stacktrace {
    private final String text;
    private final String error;
    private Stacktrace next;

    public Stacktrace(String text, String error) {
        this.text = text;
        this.error = error;
    }

    public Stacktrace(String text, Stacktrace next) {
        this(text, next.error);
        this.next = next;
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean showError) {
        return (showError ? error + "\n   " : "   ") +
                text +
                (next != null ? ("\n" + next.toString(false)) : "");
    }
}
