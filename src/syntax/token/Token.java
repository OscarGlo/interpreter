package syntax.token;

import err.InterpreterError;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Token {
    private String name;

    public Token(String name) {
        this.name = name.trim();
    }

    public Token() {
        this.name = this.getClass().getSimpleName();
    }

    // Methods for building tokens wih token arrays
    public Token(Token[] tokens) {
        throw new NotImplementedException();
    }

    protected void checkTokenNum(int length, int expected) {
        if (length != expected)
            throw new RuntimeException("Wrong number of tokens for token class " + this.getClass().getSimpleName()
                    + " (found " + length + ", expected " + expected + ")");
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
