package syntax.token;

import java.util.LinkedList;
import java.util.List;

public class NameList extends Token {
    private TName param;
    private NameList next;

    public NameList(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        param = (TName) tokens[0];

        if (tokens[2] instanceof TName)
            next = new NameList((TName) tokens[2]);
        else
            next = (NameList) tokens[2];
    }

    private NameList(TName param) {
        this.param = param;
    }

    public List<String> asList() {
        return asList(new LinkedList<>());
    }

    private List<String> asList(List<String> l) {
        l.add(this.param.getValueName());
        if (next != null)
            return next.asList(l);
        return l;
    }
}
