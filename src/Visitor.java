import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Visitor {
    private int declType; // void int array -> 0 1 2
    private int nodeValue = -114514;
    private int nodeIndex = -114514;
    private int nodePointer = -114514;
    private boolean needLValIndex = false;
    private boolean onlyVariable = false;
    private boolean canCalc;
    private final SymbolTable table = new SymbolTable();

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
        nodeValue = ctx.getValue();
        nodeIndex = -1;
        canCalc = true;
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

    public Void visitCompUnit(ASTNode ctx) {
        ArrayList<Integer> params;

        params = new ArrayList<>();
        table.allocNewFunction("getint", params, 1);

        params = new ArrayList<>();
        table.allocNewFunction("getch", params, 1);

        params = new ArrayList<>(); params.add(1);
        table.allocNewFunction("putint", params, 0);

        params = new ArrayList<>(); params.add(1);
        table.allocNewFunction("putch", params, 0);

        return visitChild(ctx);
    }

    public Void visitDecl(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstDecl(ASTNode ctx) {
        declType = 1;
        return visitChild(ctx);
    }

    public Void visitBType(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstDef(ASTNode ctx) {
        if (declType == 1) {
            String name = ctx.ident().getToken().getText();
            if (!table.checkName(name)) {
                System.out.println("name repeated: " + name);
                System.exit(-1);
            }
            Symbol symbol = table.allocInteger(name, 1);
            System.out.printf("\t%%v%d = alloca i32\n", symbol.getIndex());
            // TODO 数组
            if (ctx.hasConstInitial()) {
                visit(ctx.constInitial());
                if (canCalc) {
                    // 如果可以计算，则结果保存在 nodeValue 中
                    System.out.printf("\tstore i32 %d, i32* %%v%d\n", nodeValue, symbol.getIndex());
                    symbol.setConstValue(nodeValue);
                } else {
                    // 如果不可以计算，则返回-1
                    System.out.println("常量必须可以计算");
                    System.exit(-1);
                }
            }
        }
        return null;
    }

    public Void visitConstInitial(ASTNode ctx) { return visitChild(ctx);}

    public Void visitVarDecl(ASTNode ctx) {
        declType = 1;
        return visitChild(ctx);
    }

    public Void visitVarDef(ASTNode ctx) {
        if (declType == 1) {
            String name = ctx.ident().getToken().getText();
            if (!table.checkName(name)) {
                System.out.println("name repeated: " + name);
                System.exit(-1);
            }
            Symbol symbol = table.allocInteger(name, 0);
            System.out.printf("\t%%v%d = alloca i32\n", symbol.getIndex());
            // TODO 数组
            if (ctx.hasInitVal()) {
                visit(ctx.initVal());
                if (canCalc) {
                    // 如果可以计算，则结果保存在 nodeValue 中
                    System.out.printf("\tstore i32 %d, i32* %%v%d\n", nodeValue, symbol.getIndex());
                } else {
                    // 如果不可以计算，则结果保存在 nodeIndex 寄存器中
                    System.out.printf("\tstore i32 %%v%d, i32* %%v%d\n", nodeIndex, symbol.getIndex());
                }
            }
        }
        return null;
    }

    public Void visitInitVal(ASTNode ctx) {
        needLValIndex = true;
        visitChild(ctx);
        needLValIndex = false;
        return null;
    }

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
        if (ctx.hasLVal()) {
            int LIndex, RVal, RIdx; boolean RCan;
            needLValIndex = false; onlyVariable = true;
            visit(ctx.lVal());
            LIndex = nodePointer; onlyVariable = false; needLValIndex = true;
            visit(ctx.exp()); needLValIndex = false;
            RVal = nodeValue; RIdx = nodeIndex; RCan = canCalc;
            System.out.print("\tstore i32 ");
            if (RCan) {
                System.out.printf("%d, ", RVal);
            } else {
                System.out.printf("%%v%d, ", RIdx);
            }
            System.out.printf("i32* %%v%d\n", LIndex);
        } else if (ctx.hasRETURN()) {
            needLValIndex = true;
            visitChild(ctx);
            needLValIndex = false;
            if (canCalc)
                System.out.printf("\tret i32 %d\n", nodeValue);
            else
                System.out.printf("\tret i32 %%v%d\n", nodeIndex);
        } else {
            visitChild(ctx);
        }
        return null;
    }

    public Void visitExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitCond(ASTNode ctx) { return visitChild(ctx);}

    public Void visitLVal(ASTNode ctx) {
        String name = ctx.ident().getToken().getText();
        Symbol symbol = table.find(name);
        if (name == null || symbol == null) {
            System.out.println("变量尚未定义：" + name);
            System.exit(-1);
        }
        if (symbol.isVariable()) {
            canCalc = false;
            nodeValue = -1;
            nodePointer = table.find(name).getIndex();
            if (needLValIndex) {
                nodeIndex = table.getNewRegister();
                System.out.printf("\t%%v%d = load i32, i32* %%v%d\n", nodeIndex, table.find(name).getIndex());
            } else {
                nodeIndex = -1;
            }
        } else {
            assert (symbol.isConstant());
            if (onlyVariable) {
                System.out.println("不能对常量赋值: " + name);
                System.exit(-1);
            }
            canCalc = true;
            nodeValue = symbol.getConstValue();
            nodeIndex = nodePointer = -1;
        }
        return null;
    }

    public Void visitPrimaryExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitUnaryExp(ASTNode ctx) {
        if (ctx.hasIdent()) {
            needLValIndex = true;
            String name = ctx.ident().getToken().getText();
            if (!table.checkFunction(name)) {
                ArrayList<Integer> params = table.getFunctionParams(name);
                int type = table.getFunctionType(name);
                canCalc = false; nodeValue = -1;
                ASTNode root = ctx.funcRParams();
                if (params.size() > 0 && root.countExp() != params.size()) {
                    System.out.println("参数数量不匹配");
                    System.exit(-1);
                }
                ArrayList<Integer> paramIndex = new ArrayList<>();
                ArrayList<Integer> paramType = new ArrayList<>();
                for (int i = 0; i < params.size(); ++i) {
                    visit(root.exp(i));
                    if (canCalc) {
                        paramIndex.add(nodeValue);
                        paramType.add(0);
                    } else {
                        paramIndex.add(nodeIndex);
                        paramType.add(1);
                    }
                }

                if (type == 1) {
                    nodeIndex = table.getNewRegister();
                    System.out.printf("\t%%v%d = call i32 @%s(", nodeIndex, name);
                } else {
                    nodeIndex = -1;
                    System.out.printf("\tcall void @%s(", name);
                }
                for (int i = 0;i < params.size(); ++i) {
                    if (i != 0) System.out.print(", ");
                    int param = paramIndex.get(i), kind = paramType.get(i);
                    if (kind == 0) {
                        System.out.printf("i32 %d", param);
                    } else {
                        System.out.printf("i32 %%v%d", param);
                    }
                }
                System.out.println(")");
            } else {
                System.out.println("函数未定义:" + name);
                System.exit(-1);
            }
            needLValIndex = false;
        } else if (ctx.hasUnaryOp()) {
            visit(ctx.unaryExp());
            if (canCalc) {
                if (ctx.unaryOp().hasMINUS())
                    nodeValue = -nodeValue;
            } else {
                if (nodeValue < 0 && nodeIndex < 0) {
                    System.out.println("表达式中引用了没有返回值的函数");
                    System.exit(-1);
                }
            }
        } else {
            visitChild(ctx);
        }
        return null;
    }

    public Void visitUnaryOp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitFuncRParams(ASTNode ctx) { return visitChild(ctx);}

    public Void visitMulExp(ASTNode ctx) {
        if (ctx.hasMulExp()) {
            int LVal, RVal, LIdx, RIdx; boolean LCan, RCan;
            visit(ctx.mulExp());
            LVal = nodeValue; LIdx = nodeIndex; LCan = canCalc;
            visit(ctx.unaryExp());
            RVal = nodeValue; RIdx = nodeIndex; RCan = canCalc;
            canCalc = LCan & RCan;
            if (canCalc) {
                if (ctx.hasMUL()) {
                    nodeValue = LVal * RVal;
                    nodeIndex = -1;
                }
                if (ctx.hasDIV()) {
                    nodeValue = LVal / RVal;
                    nodeIndex = -1;
                }
                if (ctx.hasMOD()) {
                    nodeValue = LVal % RVal;
                    nodeIndex = -1;
                }
            } else {
                nodeIndex = table.getNewRegister();
                if (ctx.hasMUL())
                    System.out.printf("\t%%v%d = mul i32 ", nodeIndex);
                if (ctx.hasDIV())
                    System.out.printf("\t%%v%d = sdiv i32 ", nodeIndex);
                if (ctx.hasMOD())
                    System.out.printf("\t%%v%d = srem i32 ", nodeIndex);
                if (LCan)
                    System.out.printf("%d, ", LVal);
                else
                    System.out.printf("%%v%d, ", LIdx);
                if (RCan)
                    System.out.printf("%d\n", RVal);
                else
                    System.out.printf("%%v%d\n", RIdx);
            }
        } else {
            return visitChild(ctx);
        }
        return null;
    }

    public Void visitAddExp(ASTNode ctx) {
        if (ctx.hasAddExp()) {
            int LVal, RVal, LIdx, RIdx; boolean LCan, RCan;
            visit(ctx.addExp());
            LVal = nodeValue; LIdx = nodeIndex; LCan = canCalc;
            visit(ctx.mulExp());
            RVal = nodeValue; RIdx = nodeIndex; RCan = canCalc;
            canCalc = LCan & RCan;
            if (canCalc) {
                if (ctx.hasPLUS()) {
                    nodeValue = LVal + RVal;
                    nodeIndex = -1;
                }
                if (ctx.hasMINUS()) {
                    nodeValue = LVal - RVal;
                    nodeIndex = -1;
                }
            } else {
                nodeIndex = table.getNewRegister();
                if (ctx.hasPLUS())
                    System.out.printf("\t%%v%d = add i32 ", nodeIndex);
                if (ctx.hasMINUS())
                    System.out.printf("\t%%v%d = sub i32 ", nodeIndex);
                if (LCan)
                    System.out.printf("%d, ", LVal);
                else
                    System.out.printf("%%v%d, ", LIdx);
                if (RCan)
                    System.out.printf("%d\n", RVal);
                else
                    System.out.printf("%%v%d\n", RIdx);
            }
        } else {
            visitChild(ctx);
        }
        return null;
    }

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
