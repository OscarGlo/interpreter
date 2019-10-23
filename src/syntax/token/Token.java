package syntax.token;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Token {
    private String name, value;
    private int line, pos;

    public Token(String name, String value, int line, int pos) {
        this.name = name.trim();
        this.value = value;
        this.line = line;
        this.pos = pos;
    }

    public Token(String name, int line, int pos) {
        this(name, "", line, pos);
    }

    public Token(Token[] tokens) {
        throw new NotImplementedException();
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
