package syntax.token;

import java.util.LinkedList;
import java.util.List;

public class TokenList<T extends Token> extends Token {
    private T token;
    private TokenList<T> prev;

    @SuppressWarnings("unchecked")
    public TokenList(Token[] tokens) {
        checkTokenNum(tokens.length, 3);
        token = (T) tokens[2];

        if (tokens[0] instanceof TokenList)
            prev = (TokenList<T>) tokens[0];
        else
            prev = new TokenList<>((T) tokens[0]);
    }

    private TokenList(T token) {
        this.token = token;
    }

    TokenList() {}

    public List<T> asList() {
        return asList(new LinkedList<>());
    }

    private List<T> asList(List<T> l) {
        if (prev != null)
            prev.asList(l);
        l.add(this.token);
        return l;
    }
}
