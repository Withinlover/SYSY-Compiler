import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        // 读取输入文件
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        StringBuilder builder = new StringBuilder();
        while (scanner.hasNextLine())
            builder.append(scanner.nextLine()).append('\n');
        System.out.println(builder);
        // 重定向输出流
        PrintStream printStream = new PrintStream(new FileOutputStream(args[1]));
        System.setOut(printStream);
        // 词法分析
        Lexer lexer = new Lexer(builder.toString());
        Parser parser = new Parser(lexer.getTokens());
        // 注册外部库
        Visitor visitor = new Visitor();
        ArrayList<Symbol> params;
        params = new ArrayList<>();
        visitor.table.allocNewFunction("getint", params, 1);

        params = new ArrayList<>();
        visitor.table.allocNewFunction("getch", params, 1);

        params = new ArrayList<>(); params.add(Symbol.newVariable("test", -1));
        visitor.table.allocNewFunction("putint", params, 0);

        params = new ArrayList<>(); params.add(Symbol.newVariable("test", -1));
        visitor.table.allocNewFunction("putch", params, 0);

//        declare i32 @getarray(i32*)
//        declare void @putarray(i32, i32*)
        Symbol param; ArrayList<Integer> dims;

        param = Symbol.newVariableArray("test", -1);
        dims = new ArrayList<>(); dims.add(0);
        param.setDims(dims); param.setPointer(true);
        params = new ArrayList<>(); params.add(param);
        visitor.table.allocNewFunction("getarray", params, 1);

        param = Symbol.newVariableArray("test", -1);
        dims = new ArrayList<>(); dims.add(0);
        param.setDims(dims); param.setPointer(true);
        params = new ArrayList<>(); params.add(Symbol.newVariable("test", -1)); params.add(param);
        visitor.table.allocNewFunction("putarray", params, 0);


        System.out.print("\n");
        // 语义分析 + 中间代码生成
        visitor.visit(parser.getRoot());
    }
}
