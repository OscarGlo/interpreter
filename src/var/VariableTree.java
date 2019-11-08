package var;

import main.Main;
import syntax.token.val.Function;
import syntax.token.val.Value;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class VariableTree {
    private static final Map<String, Object> globals;
    private static VariableTree instance;

    private static Function globalFunction(java.util.function.Function<Value[], Object> fun) {
        return new Function() {
            @Override
            public Object call(Value[] values) {
                return fun.apply(values);
            }
        };
    }

    static {
        instance = new VariableTree();
        globals = new HashMap<>();

        globals.put("print", globalFunction((values) -> {
            Object val = values[0].getValue();
            System.out.println(val instanceof String ? val : Value.toString(val));
            return null;
        }));

        globals.put("eval", globalFunction((values) -> {
            Value val = (Value) Main.evaluate((String) values[0].getValue(), true);
            return val.getValue();
        }));

        globals.put("printVars", globalFunction((values) -> {
            System.out.println(instance);
            return null;
        }));
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

        while (scope != null) {
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

    @Override
    public String toString() {
        return toString(" ");
    }

    public String toString(String prefix) {
        StringBuilder str = new StringBuilder();

        for (String var : map.keySet().stream().sorted().collect(Collectors.toList()))
            str.append(prefix).append(var).append(" = ").append(Value.toString(map.get(var))).append('\n');

        if (next != null)
            str.append(next.toString(prefix.substring(0, prefix.length() - 1) + " ┗ "));
        else
            str.deleteCharAt(str.length() - 1);

        return str.toString();
    }
}
