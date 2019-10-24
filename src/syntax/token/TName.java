package syntax.token;

import var.VariableTree;

public class TName extends Value<Object> {
    private final String value;

    public TName(String value) {
        super();
        this.value = value;
    }

    public Object getValue() {
        return VariableTree.get(value);
    }

    public String getValueName() {
        return value;
    }

    @Override
    public String toString() {
        return this.getName() + "(" + this.value + ")";
    }
}
