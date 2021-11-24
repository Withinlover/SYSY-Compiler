import javax.annotation.processing.SupportedSourceVersion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.StreamSupport;

public class Visitor {
    private int declType; // void int array -> 0 1 2
    private int nodeValue = -114514;
    private int nodeIndex = -114514;
    private int nodePointer = -114514;
    private int trueBlock = -1, falseBlock = -1;
    private int currentCondIndex, currentNxtIndex;
    private Symbol currentSymbol = null;
    private boolean isGlobalVariable = false;
    private boolean needLValIndex = true;
    private boolean onlyVariable = false;
    private boolean canCalc;
    public final SymbolTable table = new SymbolTable();

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

        if (ctx.hasDecl()) {
            isGlobalVariable = true;
            visit(ctx.decl());
            isGlobalVariable = false;
        }

        if (ctx.hasFuncDef()) {
            visit(ctx.funcDef());
        }

        if (ctx.hasCompUnit())
            visit(ctx.compUnit());

        return null;
    }

    public Void visitDecl(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstDecl(ASTNode ctx) {
        declType = 1;
        return visitChild(ctx);
    }

    public Void visitBType(ASTNode ctx) { return visitChild(ctx);}

    public Void visitConstDef(ASTNode ctx) {
        if (declType == 1) {
            if (ctx.hasConstExp()) {
                String name = ctx.ident().getToken().getText();
                if (!table.checkName(name)) {
                    System.out.println("name repeated: " + name);
                    System.exit(-1);
                }
                int dim = ctx.countConstExp();
                ArrayList<Integer> dims = new ArrayList<>();
                for (int index = 0;index < dim; ++index) {
                    visit(ctx.constExp(index));
                    dims.add(nodeValue);
                }
                Symbol symbol = table.allocArray(name, 1);
                symbol.setDims(dims);
                // TODO symbol.setConstValue();
                if (isGlobalVariable) {
                    symbol.setGlobal(true);
                    System.out.printf("@%s = dso_local global ", name);
                    if (ctx.hasConstInitial()) {
                        currentArrayDims = dims;
                        currentInitialDims = -1;
                        currentInitDims = new ArrayList<>();
                        currentInitSymbol = symbol;
                        isArrayInit = true;
                        visit(ctx.constInitial());
                        currentArrayDims = null;
                        currentInitialDims = -1;
                        currentInitDims = null;
                        currentInitSymbol = null;
                        isArrayInit = false;
                        System.out.print("\n");
                    } else {
                        System.out.print("zeroinitializer\n");
                    }
                } else {
                    System.out.printf("\t%%v%d = alloca ", symbol.getIndex());
                    for (int length : dims)
                        System.out.printf("[%d x ", length);
                    System.out.print("i32");
                    for (int index = 0;index < dim; ++index)
                        System.out.print("]");
                    System.out.print("\n");
                    if (ctx.hasConstInitial()) {
                        currentArrayDims = dims;
                        currentInitialDims = -1;
                        currentInitDims = new ArrayList<>();
                        currentInitSymbol = symbol;
                        isArrayInit = true;
                        visit(ctx.constInitial());
                        currentArrayDims = null;
                        currentInitialDims = -1;
                        currentInitDims = null;
                        currentInitSymbol = null;
                        isArrayInit = false;
                    }
                }
            } else {
                String name = ctx.ident().getToken().getText();
                if (!table.checkName(name)) {
                    System.out.println("name repeated: " + name);
                    System.exit(-1);
                }
                Symbol symbol = table.allocInteger(name, 1);
                if (!isGlobalVariable)
                    System.out.printf("\t%%v%d = alloca i32\n", symbol.getIndex());
                else
                    symbol.setGlobal(true);
                int initValue = 0;
                if (ctx.hasConstInitial()) {
                    visit(ctx.constInitial());
                    if (canCalc) {
                        // 如果可以计算，则结果保存在 nodeValue 中
                        if (!isGlobalVariable)
                            System.out.printf("\tstore i32 %d, i32* %%v%d\n", nodeValue, symbol.getIndex());
                        initValue = nodeValue;
                        symbol.setConstValue(nodeValue);
                    } else {
                        // 如果不可以计算，则返回-1
                        System.out.println("常量必须可以计算");
                        System.exit(-1);
                    }
                }
                System.out.printf("@%s = dso_local global i32 %d\n\n", symbol.getName(), initValue);
            }
        }
        return null;
    }

    public Void visitConstInitial(ASTNode ctx) {
        if (isArrayInit) {
            if (ctx.hasConstExp()) {
                visitChild(ctx);
                if (currentInitDims.size() != currentArrayDims.size()) {
                    System.out.print("初始化的维度不匹配3！\n");
                    System.exit(-1);
                }

                if (isGlobalVariable) {
                    if (canCalc) {
                        System.out.printf("i32 %d", nodeValue);
                        currentInitSymbol.setConstArrayValue(currentInitDims, nodeValue);
                    }
                    else {
                        System.out.println("初始值必须编译器可计算");
                        System.exit(-1);
                    }
                } else {
                    int tmp = table.getNewRegister();
                    System.out.printf("\t%%v%d = getelementptr ", tmp);
                    for (int length : currentArrayDims)
                        System.out.printf("[%d x ", length);
                    System.out.print("i32");
                    for (int index = 0;index < currentArrayDims.size(); ++index)
                        System.out.print("]");
                    System.out.print(", ");
                    for (int length : currentArrayDims)
                        System.out.printf("[%d x ", length);
                    System.out.print("i32");
                    for (int index = 0;index < currentArrayDims.size(); ++index)
                        System.out.print("]");
                    System.out.printf("* %%v%d, i32 0", currentInitSymbol.getIndex());
                    for (int i : currentInitDims)
                        System.out.printf(", i32 %d", i);
                    System.out.print("\n");
                    System.out.print("\tstore i32 ");
                    if (canCalc) {
                        currentInitSymbol.setConstArrayValue(currentInitDims, nodeValue);
                        System.out.printf("%d, ", nodeValue);
                    }
                    else {
                        System.out.println("常量定义的右值必须可以计算！");
                        System.exit(-1);
                    }
                    System.out.printf("i32* %%v%d\n", tmp);
                    nodeIndex = tmp;
                }
            } else {
                currentInitialDims += 1;
                if (currentInitialDims >= currentArrayDims.size())  {
                    System.out.print("初始化的维度不匹配！\n");
                    System.exit(-1);
                }
                int maxDim = currentArrayDims.get(currentInitialDims);
                int nowDim = ctx.countConstInitial();
                if (currentInitialDims < currentArrayDims.size() && nowDim > maxDim) {
                    System.out.print("初始化的维度不匹配2！\n");
                    System.exit(-1);
                }
                if (isGlobalVariable) {
                    for (int index = currentInitialDims; index < currentArrayDims.size(); index++)
                        System.out.printf("[%d x ", currentArrayDims.get(index));
                    System.out.print("i32");
                    for (int index = currentInitialDims; index < currentArrayDims.size(); index++)
                        System.out.print("]");
                    System.out.print(" ");
                }

                if (isGlobalVariable)
                    System.out.print("[");
                for (int i = 0;i < nowDim; ++i)  {
                    currentInitDims.add(i);
                    visit(ctx.constInitial(i));
                    if (isGlobalVariable && (i != nowDim - 1 || nowDim < maxDim))
                        System.out.print(", ");
                    currentInitDims.remove(currentInitDims.size() - 1);
                }
                for (int i = nowDim; i < maxDim; ++i) {
                    if (isGlobalVariable) {
                        if (currentInitialDims + 1 == currentArrayDims.size()) {
                            System.out.print("i32 0");
                        } else {
                            for (int index = currentInitialDims + 1; index < currentArrayDims.size(); index++)
                                System.out.printf("[%d x ", currentArrayDims.get(index));
                            System.out.print("i32");
                            for (int index = currentInitialDims + 1; index < currentArrayDims.size(); index++)
                                System.out.print("]");
                            System.out.print(" ");
                            System.out.print("zeroinitializer");
                        }
                        if (i != maxDim - 1)
                            System.out.print(", ");
                    }
                }
                if (isGlobalVariable)
                    System.out.print("]");
                currentInitialDims -= 1;
            }
        } else {
            visitChild(ctx);
        }
        return null;
    }

    public Void visitVarDecl(ASTNode ctx) {
        declType = 1;
        return visitChild(ctx);
    }

    public Void visitVarDef(ASTNode ctx) {
        if (declType == 1) {
            if (ctx.hasConstExp()) {
                String name = ctx.ident().getToken().getText();
                if (!table.checkName(name)) {
                    System.out.println("name repeated: " + name);
                    System.exit(-1);
                }
                int dim = ctx.countConstExp();
                ArrayList<Integer> dims = new ArrayList<>();
                for (int index = 0;index < dim; ++index) {
                    visit(ctx.constExp(index));
                    dims.add(nodeValue);
                }
                Symbol symbol = table.allocArray(name, 0);
                symbol.setDims(dims);
                if (isGlobalVariable) {
                    symbol.setGlobal(true);
                    System.out.printf("@%s = dso_local global ", name);
                    if (ctx.hasInitVal()) {
                        currentArrayDims = dims;
                        currentInitialDims = -1;
                        currentInitDims = new ArrayList<>();
                        currentInitSymbol = symbol;
                        isArrayInit = true;
                        visit(ctx.initVal());
                        currentArrayDims = null;
                        currentInitialDims = -1;
                        currentInitDims = null;
                        currentInitSymbol = null;
                        isArrayInit = false;
                        System.out.print("\n");
                    } else {
                        for (int length : dims)
                            System.out.printf("[%d x ", length);
                        System.out.print("i32");
                        for (int index = 0;index < dim; ++index)
                            System.out.print("]");
                        System.out.print(" zeroinitializer\n");
                    }
                } else {
                    System.out.printf("\t%%v%d = alloca ", symbol.getIndex());
                    for (int length : dims)
                        System.out.printf("[%d x ", length);
                    System.out.print("i32");
                    for (int index = 0;index < dim; ++index)
                        System.out.print("]");
                    System.out.print("\n");
                    if (ctx.hasInitVal()) {
                        currentArrayDims = dims;
                        currentInitialDims = -1;
                        currentInitDims = new ArrayList<>();
                        currentInitSymbol = symbol;
                        isArrayInit = true;
                        visit(ctx.initVal());
                        currentArrayDims = null;
                        currentInitialDims = -1;
                        currentInitDims = null;
                        currentInitSymbol = null;
                        isArrayInit = false;
                    }
                }
            } else {
                String name = ctx.ident().getToken().getText();
                if (!table.checkName(name)) {
                    System.out.println("name repeated: " + name);
                    System.exit(-1);
                }
                Symbol symbol = table.allocInteger(name, 0);
                if (isGlobalVariable) symbol.setGlobal(true);
                if (!isGlobalVariable)
                    System.out.printf("\t%%v%d = alloca i32\n", symbol.getIndex());
                int initValue = 0;
                if (ctx.hasInitVal()) {
                    visit(ctx.initVal());
                    if (!isGlobalVariable) {
                        if (canCalc) {
                            // 如果可以计算，则结果保存在 nodeValue 中
                            System.out.printf("\tstore i32 %d, i32* %%v%d\n", nodeValue, symbol.getIndex());
                        } else {
                            // 如果不可以计算，则结果保存在 nodeIndex 寄存器中
                            System.out.printf("\tstore i32 %%v%d, i32* %%v%d\n", nodeIndex, symbol.getIndex());
                        }
                    } else {
                        if (canCalc)
                            initValue = nodeValue;
                        else {
                            System.out.println("全局变量必须用常量作为初始值");
                            System.exit(-1);
                        }
                    }
                }
                // @a = dso_local global i32 5
                if (isGlobalVariable)
                    System.out.printf("@%s = dso_local global i32 %d\n\n", symbol.getName(), initValue);
            }
        }
        return null;
    }

    private ArrayList<Integer> currentArrayDims;
    private ArrayList<Integer> currentInitDims;
    private Integer currentInitialDims = -1;
    private Symbol currentInitSymbol;
    private boolean isArrayInit = false;

    public Void visitInitVal(ASTNode ctx) {
        if (isArrayInit) {
            if (ctx.hasExp()) {
                visitChild(ctx);
                if (currentInitDims.size() != currentArrayDims.size()) {
                    System.out.print("初始化的维度不匹配3！\n");
                    System.exit(-1);
                }

                if (isGlobalVariable) {
                    if (canCalc)
                        System.out.printf("i32 %d", nodeValue);
                    else {
                        System.out.println("初始值必须编译器可计算");
                        System.exit(-1);
                    }
                } else {
                    int tmp = table.getNewRegister();
                    System.out.printf("\t%%v%d = getelementptr ", tmp);
                    for (int length : currentArrayDims)
                        System.out.printf("[%d x ", length);
                    System.out.print("i32");
                    for (int index = 0;index < currentArrayDims.size(); ++index)
                        System.out.print("]");
                    System.out.print(", ");
                    for (int length : currentArrayDims)
                        System.out.printf("[%d x ", length);
                    System.out.print("i32");
                    for (int index = 0;index < currentArrayDims.size(); ++index)
                        System.out.print("]");
                    System.out.printf("* %%v%d, i32 0", currentInitSymbol.getIndex());
                    for (int i : currentInitDims)
                        System.out.printf(", i32 %d", i);
                    System.out.print("\n");
                    System.out.print("\tstore i32 ");
                    if (canCalc) {
                        System.out.printf("%d, ", nodeValue);
                        currentInitSymbol.setConstArrayValue(currentInitDims, nodeValue);
                    }
                    else
                        System.out.printf("%%v%d, ", nodeIndex);
                    System.out.printf("i32* %%v%d\n", tmp);
                    nodeIndex = tmp;
                }
            } else {
                currentInitialDims += 1;
                if (currentInitialDims >= currentArrayDims.size())  {
                    System.out.print("初始化的维度不匹配！\n");
                    System.exit(-1);
                }
                int maxDim = currentArrayDims.get(currentInitialDims);
                int nowDim = ctx.countInitVal();
                if (currentInitialDims < currentArrayDims.size() && nowDim > maxDim) {
                    System.out.print("初始化的维度不匹配2！\n");
                    System.exit(-1);
                }
                if (isGlobalVariable) {
                    for (int index = currentInitialDims; index < currentArrayDims.size(); index++)
                        System.out.printf("[%d x ", currentArrayDims.get(index));
                    System.out.print("i32");
                    for (int index = currentInitialDims; index < currentArrayDims.size(); index++)
                        System.out.print("]");
                    System.out.print(" ");
                }

                if (isGlobalVariable)
                    System.out.print("[");
                for (int i = 0;i < nowDim; ++i)  {
                    currentInitDims.add(i);
                    visit(ctx.initVal(i));
                    if (isGlobalVariable && (i != nowDim - 1 || nowDim < maxDim))
                        System.out.print(", ");
                    currentInitDims.remove(currentInitDims.size() - 1);
                }
                for (int i = nowDim; i < maxDim; ++i) {
                    if (isGlobalVariable) {
                        if (currentInitialDims + 1 == currentArrayDims.size()) {
                            System.out.print("i32 0");
                        } else {
                            for (int index = currentInitialDims + 1; index < currentArrayDims.size(); index++)
                                System.out.printf("[%d x ", currentArrayDims.get(index));
                            System.out.print("i32");
                            for (int index = currentInitialDims + 1; index < currentArrayDims.size(); index++)
                                System.out.print("]");
                            System.out.print(" ");
                            System.out.print("zeroinitializer");
                        }
                        if (i != maxDim - 1)
                            System.out.print(", ");
                    }
                }
                if (isGlobalVariable)
                    System.out.print("]");
                currentInitialDims -= 1;
            }
        } else {
            visitChild(ctx);
        }
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

    public Void visitBlock(ASTNode ctx) {
        table.allocNewBlock();
        visitChild(ctx);
        table.exitCurrentBlock();
        return null;
    }

    public Void visitBlockItem(ASTNode ctx) { return visitChild(ctx);}

    public Void visitStmt(ASTNode ctx) {
        if (ctx.hasLVal()) {
            int LIndex, RVal, RIdx; boolean RCan; Symbol symbol;
            needLValIndex = false; onlyVariable = true;
            visit(ctx.lVal()); LIndex = nodePointer; symbol = currentSymbol;
            needLValIndex = true; onlyVariable = false;
            visit(ctx.exp());
            RVal = nodeValue; RIdx = nodeIndex; RCan = canCalc;
            System.out.print("\tstore i32 ");
            if (RCan) {
                System.out.printf("%d, ", RVal);
            } else {
                System.out.printf("%%v%d, ", RIdx);
            }
            if (symbol.isGlobal())
                System.out.printf("i32* @%s\n", symbol.getName());
            else
                System.out.printf("i32* %%v%d\n", LIndex);
        }
        else if (ctx.hasRETURN()) {
            visitChild(ctx);
            if (canCalc)
                System.out.printf("\tret i32 %d\n", nodeValue);
            else
                System.out.printf("\tret i32 %%v%d\n", nodeIndex);
        }
        else if (ctx.hasIF()) {
            int ifIndex, elseIndex, nxtIndex;

            ifIndex = table.getNewBlock();
            elseIndex = table.getNewBlock();
            nxtIndex = table.getNewBlock();
            trueBlock = ifIndex; falseBlock = elseIndex;

            visit(ctx.cond());
            ASTNode ctx_OR = ctx.cond().lOrExp();
            if (!ctx_OR.hasOR() && !ctx_OR.lAndExp().hasAND())
                System.out.printf("\tbr i1 %%v%d, label %%b%d, label %%b%d\n", nodeIndex, ifIndex, elseIndex);

            System.out.printf("\nb%d:\n", ifIndex);
            visit(ctx.stmt());
            System.out.printf("\tbr label %%b%d\n", nxtIndex);
            System.out.printf("\nb%d:\n", elseIndex);
            if (ctx.hasELSE())
                visit(ctx.stmt(1));
            System.out.printf("\tbr label %%b%d\n", nxtIndex);
            System.out.printf("\nb%d:\n", nxtIndex);

        }
        else if (ctx.hasWHILE()) {
            int condIndex, bodyIndex, nxtIndex;

            condIndex = table.getNewBlock();
            bodyIndex = table.getNewBlock();
            nxtIndex = table.getNewBlock();
            trueBlock = bodyIndex; falseBlock = nxtIndex;

            int lastCondIndex = currentCondIndex;
            int lastNxtIndex = currentNxtIndex;
            currentCondIndex = condIndex;
            currentNxtIndex = nxtIndex;

            System.out.printf("\tbr label %%b%d\n", condIndex);
            System.out.printf("\nb%d:\n", condIndex);

            visit(ctx.cond());
            ASTNode ctx_OR = ctx.cond().lOrExp();
            if (!ctx_OR.hasOR() && !ctx_OR.lAndExp().hasAND())
                System.out.printf("\tbr i1 %%v%d, label %%b%d, label %%b%d\n", nodeIndex, bodyIndex, nxtIndex);

            System.out.printf("\nb%d:\n", bodyIndex);
            visit(ctx.stmt());
            System.out.printf("\tbr label %%b%d\n", condIndex);
            System.out.printf("\nb%d:\n", nxtIndex);

            currentNxtIndex = lastNxtIndex;
            currentCondIndex = lastCondIndex;

        }
        else if (ctx.hasCONTINUE()) {
            System.out.printf("\tbr label %%b%d\n", currentCondIndex);
        }
        else if (ctx.hasBREAK()) {
            System.out.printf("\tbr label %%b%d\n", currentNxtIndex);
        }
        else {
            visitChild(ctx);
        }
        return null;
    }

    public Void visitExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitCond(ASTNode ctx) { return visitChild(ctx);}

    public Void visitLVal(ASTNode ctx) {
        String name = ctx.ident().getToken().getText();
        Symbol symbol = table.find(name);
        currentSymbol = symbol;
        if (name == null || symbol == null) {
            System.out.println("变量尚未定义：" + name);
            System.exit(-1);
        }
        if (ctx.hasExp()) {
            if (!symbol.isArray() || ctx.countExp() != symbol.getDims().size()) {
                System.out.println("使用的维度不匹配：" + symbol.getName());
                System.exit(-1);
            }
            ArrayList<Integer> val = new ArrayList<>();
            ArrayList<Boolean> can = new ArrayList<>();
            boolean allCan = true;
            for (int index = 0; index < ctx.countExp(); ++index) {
                visit(ctx.exp(index));
                can.add(canCalc); allCan &= canCalc;
                if (canCalc)
                    val.add(nodeValue);
                else
                    val.add(nodeIndex);
            }
            if (symbol.isVariable() || (symbol.isConstant() && !allCan)) {
                nodePointer = symbol.getIndex();
                int tmp = table.getNewRegister();
                System.out.printf("\t%%v%d = getelementptr ", tmp);
                for (int length : symbol.getDims())
                    System.out.printf("[%d x ", length);
                System.out.print("i32");
                for (int index = 0;index < symbol.getDims().size(); ++index)
                    System.out.print("]");
                System.out.print(", ");
                for (int length : symbol.getDims())
                    System.out.printf("[%d x ", length);
                System.out.print("i32");
                for (int index = 0;index < symbol.getDims().size(); ++index)
                    System.out.print("]");
                if (symbol.isGlobal())
                    System.out.printf("* @%s, i32 0", symbol.getName());
                else
                    System.out.printf("* %%v%d, i32 0", nodePointer);
                for (int index = 0; index < ctx.countExp(); ++index) {
                    if (can.get(index))
                        System.out.printf(", i32 %d", val.get(index));
                    else
                        System.out.printf(", i32 %%v%d", val.get(index));
                }
                // %v6 = getelementptr [2 x [3 x i32]], [2 x [3 x i32]]* %v3, i32 0, i32 0, i32 1
                System.out.print("\n");
                nodePointer = tmp;

                // nodeIndex 为 load 后的值
                if (needLValIndex) {
                    nodeIndex = table.getNewRegister();
                    System.out.printf("\t%%v%d = load i32, i32* %%v%d\n", nodeIndex, nodePointer);
                } else {
                    nodeIndex = -1;
                }
                nodeValue = -1;
                canCalc = false;
            }
            else {
                if (onlyVariable) {
                    System.out.println("不能对常量赋值: " + name);
                    System.exit(-1);
                }
                // TODO 得到正确的常量数值
                canCalc = true;
                nodeValue = symbol.getArrayValue(val);
                nodeIndex = nodePointer = -1;
            }
        } else {
            if (symbol.isArray()) {
                System.out.println("错误的将数组用为 i32");
                System.exit(-1);
            }
            if (symbol.isVariable()) {
                canCalc = false;
                nodeValue = -1;
                nodePointer = table.find(name).getIndex();
                if (needLValIndex) {
                    nodeIndex = table.getNewRegister();
                    if (!symbol.isGlobal())
                        System.out.printf("\t%%v%d = load i32, i32* %%v%d\n", nodeIndex, nodePointer);
                    else
                        System.out.printf("\t%%v%d = load i32, i32* @%s\n", nodeIndex, name);
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
        }


        return null;
    }

    public Void visitPrimaryExp(ASTNode ctx) { return visitChild(ctx);}

    public Void visitUnaryExp(ASTNode ctx) {
        if (ctx.hasIdent()) {
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
        } else if (ctx.hasUnaryOp()) {
            visit(ctx.unaryExp());
            if (canCalc) {
                if (ctx.unaryOp().hasMINUS())
                    nodeValue = -nodeValue;
                if (ctx.unaryOp().hasNOT())
                    nodeValue = nodeValue == 0 ? 1 : 0;
            } else {
                if (nodeValue < 0 && nodeIndex < 0) {
                    System.out.println("表达式中引用了没有返回值的函数");
                    System.exit(-1);
                }
                if (ctx.unaryOp().hasMINUS()) {
                    int tmp = nodeIndex;
                    nodeIndex = table.getNewRegister();
                    System.out.printf("\t%%v%d = sub i32 0, %%v%d\n", nodeIndex, tmp);
                }
                if (ctx.unaryOp().hasNOT()) {
                    // %3 = icmp eq i32 %2, 0
                    // %4 = zext i1 %3 to i32
                    int tmp1 = nodeIndex, tmp2 = table.getNewRegister(); nodeIndex = table.getNewRegister();
                    System.out.printf("\t%%v%d = icmp eq i32 %%v%d, 0\n", tmp2, tmp1);
                    System.out.printf("\t%%v%d = zext i1 %%v%d to i32\n", nodeIndex, tmp2);
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

    public Void visitRelExp(ASTNode ctx) {
        // %6 = icmp sgt i32 %4, %5
        if (ctx.getChilds().size() > 1) {
            int LVal, LIdx, RVal, RIdx; boolean LCan, RCan;
            visit(ctx.relExp());
            LVal = nodeValue; LIdx = nodeIndex; LCan = canCalc;
            visit(ctx.addExp());
            RVal = nodeValue; RIdx = nodeIndex; RCan = canCalc;

            canCalc = false;
            nodeValue = -1;
            nodeIndex = table.getNewRegister();
            System.out.printf("\t%%v%d = icmp ", nodeIndex);
            switch (ctx.getChilds().get(1).getType()) {
                case LT -> System.out.print("slt ");
                case LT_EQ -> System.out.print("sle ");
                case GT -> System.out.print("sgt ");
                case GT_EQ -> System.out.print("sge ");
            }
            System.out.print("i32 ");
            if (LCan) System.out.printf("%d, ", LVal);
            else System.out.printf("%%v%d, ", LIdx);
            if (RCan) System.out.printf("%d\n", RVal);
            else System.out.printf("%%v%d\n", RIdx);
            // %7 = zext i1 %6 to i32
            int tmp = nodeIndex; nodeIndex = table.getNewRegister();
            System.out.printf("\t%%v%d = zext i1 %%v%d to i32\n", nodeIndex, tmp);
        }
        else {
            visitChild(ctx);
        }
        return null;
    }

    public Void visitEqExp(ASTNode ctx) {
        if (ctx.hasEqExp()) {
            int LIdx, LVal, RIdx, RVal; boolean LCan, RCan;
            visit(ctx.eqExp()); LIdx = nodeIndex; LVal = nodeValue; LCan = canCalc;
            visit(ctx.relExp()); RIdx = nodeIndex; RVal = nodeValue; RCan = canCalc;
            nodeIndex = table.getNewRegister();
            // %8 = icmp eq i32 %7, 1
            if (ctx.hasEQ()) System.out.printf("\t%%v%d = icmp eq i32 ", nodeIndex);
            else System.out.printf("\t%%v%d = icmp ne i32 ", nodeIndex);
            if (LCan) System.out.printf("%d, ", LVal);
            else System.out.printf("%%v%d, ", LIdx);
            if (RCan) System.out.printf("%d\n", RVal);
            else System.out.printf("%%v%d\n", RIdx);

            int tmp = nodeIndex; nodeIndex = table.getNewRegister();
            System.out.printf("\t%%v%d = zext i1 %%v%d to i32\n", nodeIndex, tmp);


        } else {
            visitChild(ctx);
            if (canCalc) {
                nodeIndex = table.getNewRegister(); canCalc = false;
                System.out.printf("\t%%v%d = icmp ne i32 %d, 0\n", nodeIndex, nodeValue);
                int tmp = nodeIndex; nodeIndex = table.getNewRegister();
                System.out.printf("\t%%v%d = zext i1 %%v%d to i32\n", nodeIndex, tmp);
            }
        }
        return null;
    }

    public Void visitLAndExp(ASTNode ctx) { // return i1
        if (!ctx.hasAND()) {
            visitChild(ctx);
            int tmp = nodeIndex;
            nodeIndex = table.getNewRegister();
            System.out.printf("\t%%v%d = icmp ne i32 %%v%d, 0\n", nodeIndex, tmp);
            System.out.printf("\tbr i1 %%v%d, label %%b%d, label %%b%d\n", nodeIndex, trueBlock, falseBlock);
        }

        else {
            int BTrue = trueBlock, BFalse = falseBlock;
            int BLTrue = table.getNewBlock();
            trueBlock = BLTrue; falseBlock = BFalse;
            visit(ctx.lAndExp());
            System.out.printf("\nb%d:\n", BLTrue);
            visit(ctx.eqExp());
            int RIdx = nodeIndex; nodeIndex = table.getNewRegister();
            System.out.printf("\t%%v%d = icmp ne i32 %%v%d, 0\n", nodeIndex, RIdx);
            System.out.printf("\tbr i1 %%v%d, label %%b%d, label %%b%d\n", nodeIndex, BTrue, BFalse);
        }
        return null;
    }

    public Void visitLOrExp(ASTNode ctx) {
        if (!ctx.hasOR()) visitChild(ctx);
        else {
            int BTrue = trueBlock, BFalse = falseBlock;
            int BLFalse = table.getNewBlock();
            trueBlock = BTrue; falseBlock = BLFalse;
            visit(ctx.lOrExp());
            System.out.printf("\nb%d:\n", BLFalse);
            trueBlock = BTrue; falseBlock = BFalse;
            visit(ctx.lAndExp());
        }
        return null;
    }

    public Void visitConstExp(ASTNode ctx) {
        visitChild(ctx);
        if (!canCalc) {
            System.out.println("编译器无法求值");
            System.exit(-1);
        }
        return null;
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
        Parser parser = new Parser(lexer.getTokens());
        Visitor visitor = new Visitor(); visitor.DEBUG = 1;
        visitor.visit(parser.getRoot());
    }
}
