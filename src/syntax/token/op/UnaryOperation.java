package syntax.token.op;

import syntax.token.Token;
import syntax.token.Value;

public abstract class UnaryOperation<A, T> extends Operation<T> {
    interface UnaryOperator<A, T> {
        T calculate(A a);
    }

    private final UnaryOperator<A, T> operator;
    private final Value<A> a;

    @SuppressWarnings("unchecked")
    UnaryOperation(UnaryOperator<A, T> operator, boolean postFix, Token[] tokens) {
        this.operator = operator;

        checkTokenNum(tokens.length, 2);
        this.a = (Value<A>) tokens[postFix ? 0 : 1];
    }

    UnaryOperation(UnaryOperator<A, T> operator, Token[] tokens) {
        this(operator, false, tokens);
    }

    @Override
    public T getValue() {
        return operator.calculate(a.getValue());
    }
}