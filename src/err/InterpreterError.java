package err;

public class InterpreterError extends Error {
    public InterpreterError(String str) {
        super(str);
    }

    public InterpreterError(String str, int line, int pos) {
        super(str.trim() + " (line " + line + ", pos " + pos + ")");
    }
}
