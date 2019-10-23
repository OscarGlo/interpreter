package syntax;

import err.InterpreterError;
import syntax.token.Token;

import java.util.List;
import java.util.stream.Collectors;

public class TreeBuilder {
    public static List<Token> replace(List<Token> list, String pattern, Class<? extends Token> clazz) {
        String[] patArr = pattern.split("\\s");
        List<String> tokens = list.stream().map(Token::getName).collect(Collectors.toList());

        boolean replaced = true;

        while (replaced) {
            replaced = false;

            int start = -1, pos = 0;

            for (int i = 0; i < tokens.size(); i++) {
                if (tokens.get(i).equals(patArr[pos])) {
                    if (pos == 0)
                        start = i;

                    if (pos == patArr.length - 1) {
                        List<Token> subList = list.subList(start, i);
                        Token[] elems = (Token[]) subList.toArray();

                        subList.clear();
                        tokens.subList(start, i).clear();

                        try {
                            list.add(start, clazz.getConstructor(new Class[] {Token[].class}).newInstance(elems));
                            tokens.add(start, clazz.getName());
                        } catch (Exception e) {
                            throw new InterpreterError("Error when calling constructor of token class " + clazz.getName());
                        }
                    } else
                        pos++;
                } else {
                    pos = 0;
                    start = -1;
                }
            }
        }

        return list;
    }
}
