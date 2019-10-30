package var;

import main.Main;
import syntax.token.DefaultFunction;
import syntax.token.Value;

import java.util.HashMap;
import java.util.Map;

public class VariableTree {
    private static final Map<String, Object> globals;
    private static VariableTree instance;

    static {
        instance = new VariableTree();
        globals = new HashMap<>();

        globals.put("print", new DefaultFunction() {
            @Override
            public Object call(Value[] values) {
                System.out.println(values[0].getValue());
                return null;
            }
        });

        globals.put("eval", new DefaultFunction() {
            @Override
            public Object call(Value[] values) {
                Value val = (Value) Main.evaluate((String) values[0].getValue(), true);
                return val.getValue();
            }
        });
    }

    public static void addScope() {
        instance = new VariableTree(instance);
    }

    public static void delScope() {
        instance = instance.next;
    }

    public static void set(String name, Object val) {
        instance.setVar(name, val);
    }

    public static void setInScope(String name, Object val) {
        instance.setVarInScope(name, val);
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

    private VariableTree(VariableTree next) {
        this();
        this.next = next;
    }

    private void setVar(String name, Object val) {
        VariableTree scope = this;

        while (scope.next != null) {
            if (scope.map.containsKey(name)) {
                scope.setVarInScope(name, val);
                return;
            }
            scope = scope.next;
        }

        setVarInScope(name, val);
    }

    private void setVarInScope(String name, Object val) {
        map.put(name, val);
    }

    private Object getVar(String name) {
        if (map.containsKey(name))
            return map.get(name);
        else if (this.next != null)
            return next.getVar(name);
        else if (globals.containsKey(name))
            return globals.get(name);
        return null;
    }

    private void unsetVar(String name) {
        if (map.containsKey(name))
            map.remove(name);
        else if (this.next != null)
            next.unsetVar(name);
    }
}
