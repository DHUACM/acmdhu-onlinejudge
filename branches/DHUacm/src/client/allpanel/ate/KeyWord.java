package client.allpanel.ate;

import javax.swing.text.Segment;

class KeyWord {

    public KeyWord() {
        language = "";
        KEYWORDS = KEYWORDSPASCAL;
        language = "Pascal";
    }

    public void setKeyWord(String l) {
        if (l.compareToIgnoreCase("Pascal") == 0) {
            KEYWORDS = KEYWORDSPASCAL;
        } else if (l.compareToIgnoreCase("Java") == 0) {
            KEYWORDS = KEYWORDSJAVA;
        } else {
            KEYWORDS = KEYWORDSCPP;
        }
        language = l;
    }

    public String getLanguage() {
        return language;
    }

    public static boolean isKeyWord(Segment seg) {
        boolean isKey = false;
        for (int i = 0; !isKey && i < KEYWORDS.length; i++) {
            if (seg.count != KEYWORDS[i].length()) {
                continue;
            }
            isKey = true;
            for (int j = 0; isKey && j < seg.count; j++) {
                if (seg.array[seg.offset + j] != KEYWORDS[i].charAt(j)) {
                    isKey = false;
                }
            }

        }

        return isKey;
    }
    private static String KEYWORDS[];
    private String language;
    private static final String KEYWORDSJAVA[] = {"abstract", "boolean", "break", "byte", "case", "catch", "char", "class", "const", "continue",
            "default", "do", "double", "else", "extends", "final", "finally", "float", "for", "goto",
            "if", "implements", "import", "instanceof", "int", "interface", "long", "native", "new", "package",
            "private", "protected", "public", "return", "short", "static", "strictfp", "super", "switch", "synchronized",
            "this", "throw", "throws", "transient", "try", "void", "volatile", "while", "true", "false"};
    private static final String KEYWORDSPASCAL[] = {"and", "array", "begin", "case", "const", "div", "do", "downto", "else", "end",
            "file", "for", "function", "goto", "if", "in", "lable", "mod", "nil", "not",
            "of", "or", "packed", "procedure", "program", "record", "repeat", "set", "then", "to",
            "type", "until", "var", "while", "with"};
    private static final String KEYWORDSCPP[] = {"auto", "bool", "break", "case", "char", "class", "const", "continue", "default", "delete",
            "do", "double", "else", "enum", "explicit", "extern", "float", "for", "friend", "false",
            "goto", "if", "include", "inline", "int", "long", "mutable", "new", "operator", "private",
            "protected", "public", "register", "return", "short", "signed", "sizeof", "static", "static_cast", "struct",
            "switch", "this", "typedef", "true", "union", "unsigned", "virtual", "void", "while", "using",
            "namespace"};
}