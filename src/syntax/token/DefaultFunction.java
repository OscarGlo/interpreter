package syntax.token;

public abstract class DefaultFunction extends Function {
    @Override
    public abstract Object call(Value[] values);
}
