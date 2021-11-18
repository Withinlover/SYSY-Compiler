import java.util.ArrayList;

public class Rule {
    private final String name, regexp;
    private final TokenType type;

    private static final ArrayList<Rule> rules = new ArrayList<>() {{
        // IDENT
        add(new Rule("Ident", "[a-zA-Z_][_a-zA-Z0-9]*", TokenType.ident));

        // 数字
        add(new Rule("HexNumber", "(0x|0X)[0-9a-fA-F]+", TokenType.hexNumber));
        add(new Rule("OctNumber", "0[0-9]*", TokenType.octNumber));
        add(new Rule("DecNumber", "[1-9][0-9]*", TokenType.decNumber));
        // 符号
        add(new Rule("LBRACE", "\\(", TokenType.braceL));
        add(new Rule("RBRACE", "\\)", TokenType.braceR));
        add(new Rule("LBRACE_PLUS", "\\{", TokenType.braceLPlus));
        add(new Rule("RBRACE_PLUS", "\\}", TokenType.braceRPlus));
        add(new Rule("LBRACE_MEDIUM", "\\[", TokenType.braceLMedium));
        add(new Rule("RBRACE_MEDIUM", "\\]", TokenType.braceRMedium));
        add(new Rule("SEMICOLON", ";", TokenType.semicolon));
        add(new Rule("EQUAL", "==", TokenType.equal));
        add(new Rule("ASSIGNMENT", "=", TokenType.assignment));
        add(new Rule("NOTEQUAL", "!=", TokenType.notEqual));
        add(new Rule("COMMA", ",", TokenType.comma));
        add(new Rule("ADD", "\\+", TokenType.add));
        add(new Rule("MIN", "\\-", TokenType.mns));
        add(new Rule("MUL", "\\*", TokenType.mul));
        add(new Rule("DIV", "\\/", TokenType.div));
        add(new Rule("MOD", "\\%", TokenType.mod));
        add(new Rule("NOT", "!", TokenType.not));
        add(new Rule("GTEQ", ">=", TokenType.gteq));
        add(new Rule("LTEQ", "<=", TokenType.lteq));
        add(new Rule("GT", ">", TokenType.gt));
        add(new Rule("LT", "<", TokenType.lt));
        add(new Rule("AND", "&&", TokenType.and));
        add(new Rule("OR", "\\|\\|", TokenType.or));


        // 关键字
        add(new Rule("INT", "int", TokenType.INT));
//        add(new Rule("MAIN", "main", TokenType.MAIN));
        add(new Rule("RETURN", "return", TokenType.RETURN));
        add(new Rule("CONST", "const", TokenType.CONST));
        add(new Rule("VOID", "void", TokenType.VOID));
        add(new Rule("IF", "if", TokenType.IF));
        add(new Rule("ELSE", "else", TokenType.ELSE));
        add(new Rule("WHILE", "while", TokenType.WHILE));
        add(new Rule("BREAK", "break", TokenType.BREAK));
        add(new Rule("CONTINUE", "continue", TokenType.CONTINUE));

        // 空格
        add(new Rule("WHITE", "[ \t\n\r]+", TokenType.WHITE));
        add(new Rule("Comment", "//.*\\n?|/\\*[\\s\\S\\r\\n]*?\\*/", TokenType.COMMENT));
    }};

    public static ArrayList<Rule> getRules() {
        return rules;
    }

    public Rule(String name, String regexp, TokenType type) {
        this.name = name;
        this.regexp = regexp;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getRegexp() {
        return regexp;
    }

    public TokenType getType() {
        return type;
    }
    public boolean check(String str) {
        return str.matches(regexp);
    }
}
