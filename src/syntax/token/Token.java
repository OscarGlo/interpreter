package syntax.token;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Token {
    private String name;
    private int line, pos;

    public Token(String name) {
        this.name = name.trim();
    }

    // Methods for building tokens wih token arrays
    public Token(Token[] tokens) {
        throw new NotImplementedException();
    }

    public Token() {
        this.name = this.getClass().getSimpleName();
    }

    public Token(Token token) {
        this();
        this.line = token.line;
        this.pos = token.pos;
    }

    protected void checkTokenNum(int length, int expected) {
        if (length != expected)
            throw new RuntimeException("Wrong number of tokens for token class " + this.getClass().getSimpleName()
                    + " (found " + length + ", expected " + expected + ")");
    }

    public String getName() {
        return name;
    }

    public boolean matches(String pattern) {
        boolean hasSuper = false;
        try {
            boolean hasType = true;

            Class<?> curClass = getClass();
            if (pattern.contains("<")) {
                String typeParam = pattern.substring(pattern.indexOf('<') + 1, pattern.length() - 1);
                pattern = pattern.substring(0, pattern.indexOf('<'));

                hasType = ((Value) this).getType().getSimpleName().equals(typeParam);
            }

            Class<?> supClass = Class.forName("syntax.token." + pattern);
            hasSuper = supClass.isAssignableFrom(curClass) && hasType;
        } catch (Exception ignored) {}

        return hasSuper || name.equals(pattern);
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
