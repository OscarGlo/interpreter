package syntax.token;

import java.util.LinkedList;
import java.util.List;

public class ParamList extends Token {
    private TName param;
    private ParamList next;


    public ParamList(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        param = (TName) tokens[0];

        if (tokens[2] instanceof TName)
            next = new ParamList((TName) tokens[2]);
        else
            next = (ParamList) tokens[2];
    }

    private ParamList(TName param) {
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
