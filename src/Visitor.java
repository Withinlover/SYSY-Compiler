import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Visitor {
    private int nodeValue;
    private int dep = 0;
    public int DEBUG = 0;
    public Visitor() { }

    public Void visitChild(ASTNode ctx) {
        ArrayList<ASTNode> childs = ctx.getChilds();
        for (ASTNode child : childs)
            visit(child);
        return null;
    }

    public Void visit(ASTNode ctx) {
        dep = dep + 1;
        if (DEBUG == 1) {
            for (int i = 0; i < dep; ++i)
                System.out.print("- ");
            System.out.print(ctx.getType());
            if (ctx.getType() == ASTNodeType.number)
                System.out.print("\t Content: [\"" + ctx.getValue() + "\"]");
            if (ctx.getToken() != null) {
                System.out.print("\t Content: [\"" + ctx.getToken().getText() + "\"]");
            }
            System.out.println("");
            visitChild(ctx);
        } else {
            switch (ctx.getType()) {case number -> visitNumber(ctx);
                case ident -> visitIdent(ctx);
                case CONST -> visitCONST(ctx);
                case INT -> visitINT(ctx);
                case VOID -> visitVOID(ctx);
                case IF -> visitIF(ctx);
                case ELSE -> visitELSE(ctx);
                case WHILE -> visitWHILE(ctx);
                case BREAK -> visitBREAK(ctx);
                case CONTINUE -> visitCONTINUE(ctx);
                case RETURN -> visitRETURN(ctx);
                case COMMA -> visitCOMMA(ctx);
                case SEMICOLON -> visitSEMICOLON(ctx);
                case ASSIGN -> visitASSIGN(ctx);
                case BRACE_L_PLUS -> visitBRACE_L_PLUS(ctx);
                case BRACE_R_PLUS -> visitBRACE_R_PLUS(ctx);
                case BRACE_L_MEDIUM -> visitBRACE_L_MEDIUM(ctx);
                case BRACE_R_MEDIUM -> visitBRACE_R_MEDIUM(ctx);
                case BRACE_L -> visitBRACE_L(ctx);
                case BRACE_R -> visitBRACE_R(ctx);
                case PLUS -> visitPLUS(ctx);
                case MINUS -> visitMINUS(ctx);
                case MUL -> visitMUL(ctx);
                case DIV -> visitDIV(ctx);
                case NOT -> visitNOT(ctx);
                case MOD -> visitMOD(ctx);
                case GT -> visitGT(ctx);
                case LT -> visitLT(ctx);
                case GT_EQ -> visitGT_EQ(ctx);
                case LT_EQ -> visitLT_EQ(ctx);
                case EQ -> visitEQ(ctx);
                case NOT_EQ -> visitNOT_EQ(ctx);
                case AND -> visitAND(ctx);
                case OR -> visitOR(ctx);
                case compUnit -> visitCompUnit(ctx);
                case decl -> visitDecl(ctx);
                case constDecl -> visitConstDecl(ctx);
                case BType -> visitBType(ctx);
                case constDef -> visitConstDef(ctx);
                case constInitial -> visitConstInitial(ctx);
                case varDecl -> visitVarDecl(ctx);
                case varDef -> visitVarDef(ctx);
                case initVal -> visitInitVal(ctx);
                case funcDef -> visitFuncDef(ctx);
                case funcType -> visitFuncType(ctx);
                case funcFParams -> visitFuncFParams(ctx);
                case funcFParam -> visitFuncFParam(ctx);
                case block -> visitBlock(ctx);
                case blockItem -> visitBlockItem(ctx);
                case stmt -> visitStmt(ctx);
                case exp -> visitExp(ctx);
                case cond -> visitCond(ctx);
                case lVal -> visitLVal(ctx);
                case primaryExp -> visitPrimaryExp(ctx);
                case unaryExp -> visitUnaryExp(ctx);
                case unaryOp -> visitUnaryOp(ctx);
                case funcRParams -> visitFuncRParams(ctx);
                case mulExp -> visitMulExp(ctx);
                case addExp -> visitAddExp(ctx);
                case relExp -> visitRelExp(ctx);
                case eqExp -> visitEqExp(ctx);
                case lAndExp -> visitLAndExp(ctx);
                case lOrExp -> visitLOrExp(ctx);
                case constExp -> visitConstExp(ctx);
            }
        }
        dep = dep - 1;
        return null;
    }

    public Void visitNumber(ASTNode ctx) {
        System.out.println(ctx.getValue());
        return visitChild(ctx);
    }

    public Void visitIdent(ASTNode ctx) { return visitChild(ctx);}

    public Void visitCONST(ASTNode ctx) { return visitChild(ctx);}

    public Void visitINT(ASTNode ctx) { return visitChild(ctx);}

    public Void visitVOID(ASTNode ctx) { return visitChild(ctx);}

    public Void visitIF(ASTNode ctx) { return visitChild(ctx);}

    public Void visitELSE(ASTNode ctx) { return visitChild(ctx);}

    public Void visitWHILE(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBREAK(ASTNode ctx) { return visitChild(ctx);}

    public Void visitCONTINUE(ASTNode ctx) { return visitChild(ctx);}

    public Void visitRETURN(ASTNode ctx) { return visitChild(ctx);}

    public Void visitCOMMA(ASTNode ctx) { return visitChild(ctx);}

    public Void visitSEMICOLON(ASTNode ctx) { return visitChild(ctx);}

    public Void visitASSIGN(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBRACE_L_PLUS(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBRACE_R_PLUS(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBRACE_L_MEDIUM(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBRACE_R_MEDIUM(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBRACE_L(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBRACE_R(ASTNode ctx) { return visitChild(ctx);}

    public Void visitPLUS(ASTNode ctx) { return visitChild(ctx);}

    public Void visitMINUS(ASTNode ctx) { return visitChild(ctx);}

    public Void visitMUL(ASTNode ctx) { return visitChild(ctx);}

    public Void visitDIV(ASTNode ctx) { return visitChild(ctx);}

    public Void visitNOT(ASTNode ctx) { return visitChild(ctx);}

    public Void visitMOD(ASTNode ctx) { return visitChild(ctx);}

    public Void visitGT(ASTNode ctx) { return visitChild(ctx);}

    public Void visitLT(ASTNode ctx) { return visitChild(ctx);}

    public Void visitGT_EQ(ASTNode ctx) { return visitChild(ctx);}

    public Void visitLT_EQ(ASTNode ctx) { return visitChild(ctx);}

    public Void visitEQ(ASTNode ctx) { return visitChild(ctx);}

    public Void visitNOT_EQ(ASTNode ctx) { return visitChild(ctx);}

    public Void visitAND(ASTNode ctx) { return visitChild(ctx);}

    public Void visitOR(ASTNode ctx) { return visitChild(ctx);}

    public Void visitCompUnit(ASTNode ctx) { return visitChild(ctx);}

    public Void visitDecl(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstDecl(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBType(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstDef(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstInitial(ASTNode ctx) { return visitChild(ctx);}

    public Void visitVarDecl(ASTNode ctx) { return visitChild(ctx);}

    public Void visitVarDef(ASTNode ctx) { return visitChild(ctx);}

    public Void visitInitVal(ASTNode ctx) { return visitChild(ctx);}

    public Void visitFuncDef(ASTNode ctx) {
        String res = "define dso_local i32 ";
        res = res + "@" + ctx.ident().getToken().getText();
        res = res + "() {";
        System.out.println(res);
        visit(ctx.block());
        res = "}";
        System.out.println(res);
        return null;
    }

    public Void visitFuncType(ASTNode ctx) { return visitChild(ctx);}

    public Void visitFuncFParams(ASTNode ctx) { return visitChild(ctx);}

    public Void visitFuncFParam(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBlock(ASTNode ctx) { return visitChild(ctx);}

    public Void visitBlockItem(ASTNode ctx) { return visitChild(ctx);}

    public Void visitStmt(ASTNode ctx) {
        String res = "\t" + "ret i32 ";
        System.out.print(res);
        visit(ctx.exp());
        return null;
    }

    public Void visitExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitCond(ASTNode ctx) { return visitChild(ctx);}

    public Void visitLVal(ASTNode ctx) { return visitChild(ctx);}

    public Void visitPrimaryExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitUnaryExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitUnaryOp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitFuncRParams(ASTNode ctx) { return visitChild(ctx);}

    public Void visitMulExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitAddExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitRelExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitEqExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitLAndExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitLOrExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstExp(ASTNode ctx) { return visitChild(ctx);}

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
        Parser parser = new Parser(lexer.getTokens());
        Visitor visitor = new Visitor(); visitor.DEBUG = 1;
        visitor.visit(parser.getRoot());
    }
}
