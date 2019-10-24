package syntax;

import syntax.token.Token;
import util.Reader;

import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TreeBuilder {
    private static Map<String, String> syntax;

    static {
        try {
            syntax = Reader.getMap("syntax.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean replace(List<Token> list, String pattern, Class<? extends Token> clazz, String name) {
        // Get token class constructor
        Constructor<? extends Token> constr = null;
        try {
            if (clazz != null)
                constr = clazz.getConstructor(Token[].class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] patArr = pattern.split("\\s");

        boolean replaced, replacedOnce = false;

        replaced = false;

        int start = -1, pos = 0;

        for (int i = 0; i < list.size(); i++) {
            String tokPat = patArr[pos];
            Token tok = list.get(i);

            boolean hasSuper = false;
            try {
                boolean hasType = true;

                Class<?> curClass = tok.getClass();
                    /*if (tokPat.contains("<")) {
                        String typeParam = tokPat.substring(tokPat.indexOf('<') + 1, tokPat.length() - 1);
                        tokPat = tokPat.substring(0, tokPat.indexOf('<'));

                        hasType = ((Value) tok).getType().getSimpleName().equals(typeParam);
                    }*/

                Class<?> supClass = Class.forName("syntax.token." + tokPat);
                hasSuper = supClass.isAssignableFrom(curClass) && hasType;
            } catch (Exception ignored) {
            }

            if (hasSuper || tok.getName().equals(tokPat)) {
                if (pos == 0)
                    start = i;

                // End of match, replace
                if (pos == patArr.length - 1) {
                    List<Token> subList = list.subList(start, i + 1);
                    Token[] elems = Arrays.copyOf(subList.toArray(), subList.size(), Token[].class);

                    subList.clear(); // Clear used tokens in parameter list

                    if (clazz == null) { // Static terminal token
                        list.add(start, new Token(name));
                        return true;
                    } else {
                        // Token class
                        try {
                            // Insert an instance of the new token class
                            list.add(start, constr.newInstance((Object) elems));
                            return true;
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    // Reset values
                    start = -1;
                    pos = 0;
                } else
                    pos++;
            } else {
                pos = 0;
                start = -1;
            }
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    public static void build(List<Token> tokens) {
        boolean replacedOne;
        do {
            do {
                System.out.println(Arrays.toString(tokens.toArray()));

                replacedOne = false;
                for (String pattern : syntax.keySet()) {
                    Class<? extends Token> clazz = null;
                    try {
                        clazz = (Class<? extends Token>) Class.forName("syntax.token." + syntax.get(pattern));
                    } catch (ClassNotFoundException ignored) {
                    }

                    if (replace(tokens, pattern, clazz, clazz == null ? syntax.get(pattern) : null)) {
                        replacedOne = true;
                        break;
                    }
                }
            } while (replacedOne);
        } while (tokens.removeIf(t -> t.getName().equals("SEP")));
    }
}
