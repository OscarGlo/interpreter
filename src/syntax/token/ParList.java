package syntax.token;

import java.util.List;

public class ParList<T extends Token> extends TokenList<T> {
    private final TokenList<T> list;

    @SuppressWarnings("unchecked")
    public ParList(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        list = (TokenList<T>) tokens[1];
    }

    @Override
    public List<T> asList() {
        return list.asList();
    }

    @Override
    public String toString() {
        return "( " + list.toString() + " )";
    }
}
