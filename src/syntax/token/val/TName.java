package syntax.token.val;

import var.VariableTree;

public class TName extends Value<Object> {
    private final String value;

    public TName(String value, int line, int pos) {
        this.value = value;
        setLine(line);
        setPos(pos);
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
