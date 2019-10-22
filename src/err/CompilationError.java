package err;

public class CompilationError extends Error {
    public CompilationError(String str) {
        super(str);
    }

    public CompilationError(String str, int line, int pos) {
        super(str.trim() + " (line " + line + ", pos " + pos + ")");
    }
}
