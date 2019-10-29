package var;

import syntax.token.DefaultFunction;
import syntax.token.Function;
import syntax.token.Value;

import java.util.HashMap;
import java.util.Map;

public class VariableTree {
    private static final VariableTree instance;

    private static final Map<String, Object> globals;

    static {
        instance = new VariableTree();
        globals = new HashMap<>();

        globals.put("print", new DefaultFunction() {
            @Override
            public Object call(Value[] values) {
                System.out.println(values[0]);
                return null;
            }
        });
    }

    public static void addScope() {
        instance.addMap();
    }

    public static void delScope() {
        instance.delMap();
    }

    public static void set(String name, Object val) {
        instance.setVar(name, val);
    }

    public static Object get(String name) {
        return instance.getVar(name);
    }

    public static void unset(String name) {
        instance.unsetVar(name);
    }

    private Map<String, Object> map;
    private VariableTree next;

    private VariableTree() {
        this.map = new HashMap<>();
    }

    private void addMap() {
        if (this.next != null)
            next.addMap();
        else
            this.next = new VariableTree();
    }

    private void delMap() {
        if (this.next != null && this.next.next != null)
            this.next.delMap();
        else
            this.next = null;
    }

    private void setVar(String name, Object val) {
        if (globals.containsKey(name))
            return;

        if (this.next == null || map.containsKey(name))
            map.put(name, val);
        else
            next.setVar(name, val);
    }

    private Object getVar(String name) {
        if (globals.containsKey(name))
            return globals.get(name);

        if (map.containsKey(name))
            return map.get(name);
        else if (this.next != null)
            return next.getVar(name);
        return null;
    }

    private void unsetVar(String name) {
        if (map.containsKey(name))
            map.remove(name);
        else if (this.next != null)
            next.unsetVar(name);
    }
}
