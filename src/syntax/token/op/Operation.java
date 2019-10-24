package syntax.token.op;

import syntax.token.Value;

import java.lang.reflect.ParameterizedType;

public abstract class Operation<T> extends Value<T> {
    public Operation() {}

    @Override
    public abstract T getValue();

    @Override
    @SuppressWarnings("unchecked")
    public Class<T> getType() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public String toString() {
        return this.getName();
    }
}
