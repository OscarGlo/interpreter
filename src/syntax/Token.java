package syntax;

public class Token {
    private String name, value;
    private int line, pos;

    Token(String name, String value, int line, int pos) {
        this.name = name.trim();
        this.value = value;
        this.line = line;
        this.pos = pos;
    }

    Token(String name, int line, int pos) {
        this(name, "", line, pos);
    }

    public String getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public int getLine() {
        return line;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return this.name + (this.value.equals("") ? "" : "(" + this.value + ")");
    }
}
