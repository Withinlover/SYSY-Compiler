import java.util.ArrayList;

public class Token {
    private String name, text;
    private TokenType type;

    public Token(String name, String text, TokenType type) {
        this.name = name;
        this.text = text;
        this.type = type;
    }

    public Token(String text) {
        this.text = text;
        ArrayList<Rule> rules = Rule.getRules();
        for (Rule rule : rules) {
            if (text.matches(rule.getRegexp())) {
                this.name = rule.getName();
                this.type = rule.getType();
            }
        }
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }

    public TokenType getType() {
        return type;
    }

    public boolean isWhite() {
        return text.matches("[ \t\n\r]+");
    }

    public boolean isComment() {
        return text.matches("//.*\\n?|/\\*[\\s\\S\\r\\n]*?\\*/");
    }

    @Override
    public String toString() {
        return String.format("Token: [%-16s], Content: \"%s\"", this.name, this.text);
    }
}
