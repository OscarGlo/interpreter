package var;

public class Value {
    private Object val;
    private Class clazz;

    public Value(Object val, Class clazz) {
        this.val = val;
        this.clazz = clazz;
    }

    public Object getVal() {
        return val;
    }

    public Class getClazz() {
        return clazz;
    }
}
