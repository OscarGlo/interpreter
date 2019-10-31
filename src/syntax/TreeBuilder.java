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

        String negLookbehind = "", first = patArr[0];
        // Parse negative lookbehind and remove from pattern
        if (first.matches("\\(-[A-Za-z]+\\)")) {
            negLookbehind = first.substring(2, first.length() - 1);
            patArr = Arrays.copyOfRange(patArr, 1, patArr.length);
        }
        String negLookahead = "", last = patArr[patArr.length];
        // Parse negative lookbehind and remove from pattern
        if (last.matches("\\(-[A-Za-z]+\\)")) {
            negLookahead = last.substring(2, last.length() - 1);
            patArr = Arrays.copyOfRange(patArr, 1, patArr.length);
        }

        int start = -1, pos = 0;

        for (int i = 0; i < list.size(); i++) {
            String pat = patArr[pos];
            Token tok = list.get(i);

            // Ignore if previous token matches negative lookbehind or potential last token matches negative lookahead
            if (i == start) {
                if (i > 0 && negLookbehind.equals("")) {
                    if (list.get(i - 1).matches(negLookbehind))
                        continue;
                } else if (i + patArr.length < list.size() && negLookahead.equals("")) {
                    if (list.get(i + patArr.length).matches(negLookahead))
                        continue;
                }
            }

            boolean match = false;
            if (pat.contains("|")) {
                for (String tName : pat.split("\\|"))
                    if (tok.matches(tName)) {
                        match = true;
                        break;
                    }
            } else {
                match = tok.matches(pat);
            }

            if (match) {
                // First character match, save starting position
                if (pos == 0)
                    start = i;

                // End of match, replace
                if (pos == patArr.length - 1) {
                    List<Token> subList = list.subList(start, i + 1);
                    Token[] elems = Arrays.copyOf(subList.toArray(), subList.size(), Token[].class);

                    subList.clear(); // Clear used tokens in parameter list

                    if (clazz == null) {
                        // Static terminal token
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
                } else
                    pos++;
            } else {
                pos = 0;
                start = -1;
            }
        }

        return false;
    }

    public static void build(List<Token> tokens) {
        build(tokens, false);
    }

    @SuppressWarnings("unchecked")
    public static void build(List<Token> tokens, boolean debug) {
        // First, recursively call to treat parentheses
        boolean replacedPar;
        do {
            replacedPar = false;
            int parIndex = -1, lPar = 0, rPar = 0;

            for (int i = 0; i < tokens.size(); i++) {
                Token tok = tokens.get(i);

                if (tok.getName().equals("LPAR")) {
                    if (parIndex == -1)
                        parIndex = i;
                    lPar++;
                } else if (tok.getName().equals("RPAR")) {
                    rPar++;
                    if (rPar == lPar) {
                        if ((parIndex != 0 || i != tokens.size() - 1) && i > parIndex + 1) {
                            TreeBuilder.build(tokens.subList(parIndex, i + 1), debug);
                            replacedPar = true;
                            break;
                        } else {
                            parIndex = -1;
                            rPar = 0;
                            lPar = 0;
                        }
                    }
                }
            }
        } while (replacedPar);

        boolean replaced;
        do {
            if (debug) System.out.println(Arrays.toString(tokens.toArray()));

            replaced = false;
            for (String pattern : syntax.keySet()) {
                Class<? extends Token> clazz = null;
                try {
                    clazz = (Class<? extends Token>) Class.forName("syntax.token." + syntax.get(pattern));
                } catch (ClassNotFoundException ignored) {
                }

                if (replace(tokens, pattern, clazz, clazz == null ? syntax.get(pattern) : null)) {
                    replaced = true;
                    break;
                }
            }
        } while (replaced);
    }
}
