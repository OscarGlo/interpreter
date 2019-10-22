package var;

import java.util.HashMap;
import java.util.Map;

public class VariableTree {
    private static VariableTree instance;

    static {
        instance = new VariableTree();
    }

    private Map<String, Value> map;
    private VariableTree next;

    private VariableTree() {
        this.map = new HashMap<>();
    }

    public static void addScope() {
        instance.addMap();
    }

    public static void delScope() {
        instance.delMap();
    }

    public static void set(String name, String val) {
        instance.setVar(name, val);
    }

    public static void unset(String name, String val) {
        instance.setVar(name, val);
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

    private void setVar(String name, String val) {
        if (this.next == null || map.containsKey(name))
            map.put(name, new Value(val));
        else
            next.setVar(name, val);
    }

    private void unsetVar(String name) {
        if (map.containsKey(name))
            map.remove(name);
        else if (this.next != null)
            next.unsetVar(name);
    }
}
