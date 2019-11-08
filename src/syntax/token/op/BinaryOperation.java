package syntax.token.op;

import err.Stacktrace;
import syntax.token.Token;
import syntax.token.val.Value;

public abstract class BinaryOperation<A, B, T> extends Operation<T> {
    interface BinaryOperator<A, B, T> {
        T calculate(A a, B b);
    }

    private final BinaryOperator<A, B, T> operator;
    private Value<A> a;
    private Value<B> b;

    @SuppressWarnings("unchecked")
    BinaryOperation(BinaryOperator<A, B, T> operator, Token[] tokens) {
        super(tokens[1]);

        this.operator = operator;

        checkTokenNum(tokens.length, 3);
        this.a = (Value<A>) tokens[0];
        this.b = (Value<B>) tokens[2];
    }

    @Override
    public T getValue() {
        try {
            return operator.calculate(a.getValue(), b.getValue());
        } catch (Throwable t) {
            Stacktrace stack = (a.getStacktrace() != null ? a.getStacktrace() : b.getStacktrace());
            throw makeStacktrace(t, "Operation error", stack);
        }
    }
}
