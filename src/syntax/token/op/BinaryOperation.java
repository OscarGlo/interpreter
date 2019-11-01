package syntax.token.op;

import syntax.token.Token;
import syntax.token.Value;

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
        return operator.calculate(a.getValue(), b.getValue());
    }
}
