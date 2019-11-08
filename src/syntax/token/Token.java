package syntax.token;

import err.InterpreterError;
import err.Stacktrace;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import syntax.token.val.Value;

public class Token {
    private String name;
    private int line, pos;
    protected Stacktrace stack;

    public Token(String name, int line, int pos) {
        this.name = name.trim();
        this.line = line;
        this.pos = pos;
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
        } catch (Exception ignored) {
        }

        return hasSuper || name.equals(pattern);
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public Stacktrace getStacktrace() {
        return this.stack;
    }

    protected InterpreterError makeStacktrace(Throwable t, String error, Stacktrace stack) {
        String text = "at " + this.getClass().getSimpleName() + " (line " + line + ", pos " + pos + ")";
        if (t instanceof InterpreterError)
            if (stack != null)
                this.stack = new Stacktrace(text, stack);
            else
                this.stack = new Stacktrace(text, t.getMessage());
        else {
            t.printStackTrace();
            this.stack = new Stacktrace(text, error);
        }

        return new InterpreterError(error);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
