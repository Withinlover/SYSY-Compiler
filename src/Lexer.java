import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {

    private final String file;

    public Lexer(String file) {
        this.file = file.replaceAll("//.*\\n?|/\\*[\\s\\S\\r\\n]*?\\*/", " ");
    }

    public ArrayList<Token> getTokens() {
        ArrayList<Token> res = new ArrayList<>();

        // 构建词法分析器
        ArrayList<Rule> rules = Rule.getRules();
        StringBuilder str = new StringBuilder();
        str.append("(?!)");
        for (Rule item : rules)
            str.append("|(").append(item.getRegexp()).append(")");

        // 逐个查找
        Pattern pattern = Pattern.compile(str.toString());
        Matcher matcher = pattern.matcher(file);
        while (matcher.find()) {
            String text = file.substring(matcher.start(), matcher.end());
            Token token = new Token(text);
            if (!token.isWhite() && !token.isComment())
                res.add(new Token(text));
        }
        return res;
    }

    public static void main(String[] args) throws FileNotFoundException {
        // 读取输入文件
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine())
            builder.append(scanner.nextLine()).append('\n');
        // 重定向输出流
        PrintStream printStream = new PrintStream(new FileOutputStream(args[1]));
        System.setOut(printStream);
        // 词法分析
        Lexer lexer = new Lexer(builder.toString());
        for (Token token : lexer.getTokens())
            System.out.println(token);
    }
}
