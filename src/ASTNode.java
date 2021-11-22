import java.util.ArrayList;

public class ASTNode {
    private final ASTNodeType type;
    private final ArrayList<ASTNode> childs = new ArrayList<>();
    private int value;
    private String name;
    private Token token;

    public ASTNode(ASTNodeType type) {
        this.type = type;
    }

    public void addChild(ASTNode child) {
        if (child != null)
            childs.add(child);
    }

    public ArrayList<ASTNode> getChilds() {
        return childs;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public ASTNodeType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public boolean has(ASTNodeType type) {
        for (ASTNode child : childs)
            if (child.type == type)
                return true;
        return false;
    }

    public int count(ASTNodeType type) {
        int res = 0;
        for (ASTNode child : childs)
            if (child.type == type)
                res++;
        return res;
    }

    public ASTNode get(ASTNodeType type, int index) {
        int count = 0;
        for (ASTNode child : childs)
            if (child.type == type)
                if (count < index)
                    count++;
                else
                    return child;
        return null;
    }

    public boolean hasNumber() {return has(ASTNodeType.number);}
    public int countNumber() {return count(ASTNodeType.number);}
    public ASTNode number(int index) {return get(ASTNodeType.number, index);}
    public ASTNode number() {return number(0);}

    public boolean hasIdent() {return has(ASTNodeType.ident);}
    public int countIdent() {return count(ASTNodeType.ident);}
    public ASTNode ident(int index) {return get(ASTNodeType.ident, index);}
    public ASTNode ident() {return ident(0);}

    public boolean hasCONST() {return has(ASTNodeType.CONST);}
    public int countCONST() {return count(ASTNodeType.CONST);}
    public ASTNode CONST(int index) {return get(ASTNodeType.CONST, index);}
    public ASTNode CONST() {return CONST(0);}

    public boolean hasINT() {return has(ASTNodeType.INT);}
    public int countINT() {return count(ASTNodeType.INT);}
    public ASTNode INT(int index) {return get(ASTNodeType.INT, index);}
    public ASTNode INT() {return INT(0);}

    public boolean hasVOID() {return has(ASTNodeType.VOID);}
    public int countVOID() {return count(ASTNodeType.VOID);}
    public ASTNode VOID(int index) {return get(ASTNodeType.VOID, index);}
    public ASTNode VOID() {return VOID(0);}

    public boolean hasIF() {return has(ASTNodeType.IF);}
    public int countIF() {return count(ASTNodeType.IF);}
    public ASTNode IF(int index) {return get(ASTNodeType.IF, index);}
    public ASTNode IF() {return IF(0);}

    public boolean hasELSE() {return has(ASTNodeType.ELSE);}
    public int countELSE() {return count(ASTNodeType.ELSE);}
    public ASTNode ELSE(int index) {return get(ASTNodeType.ELSE, index);}
    public ASTNode ELSE() {return ELSE(0);}

    public boolean hasWHILE() {return has(ASTNodeType.WHILE);}
    public int countWHILE() {return count(ASTNodeType.WHILE);}
    public ASTNode WHILE(int index) {return get(ASTNodeType.WHILE, index);}
    public ASTNode WHILE() {return WHILE(0);}

    public boolean hasBREAK() {return has(ASTNodeType.BREAK);}
    public int countBREAK() {return count(ASTNodeType.BREAK);}
    public ASTNode BREAK(int index) {return get(ASTNodeType.BREAK, index);}
    public ASTNode BREAK() {return BREAK(0);}

    public boolean hasCONTINUE() {return has(ASTNodeType.CONTINUE);}
    public int countCONTINUE() {return count(ASTNodeType.CONTINUE);}
    public ASTNode CONTINUE(int index) {return get(ASTNodeType.CONTINUE, index);}
    public ASTNode CONTINUE() {return CONTINUE(0);}

    public boolean hasRETURN() {return has(ASTNodeType.RETURN);}
    public int countRETURN() {return count(ASTNodeType.RETURN);}
    public ASTNode RETURN(int index) {return get(ASTNodeType.RETURN, index);}
    public ASTNode RETURN() {return RETURN(0);}

    public boolean hasCOMMA() {return has(ASTNodeType.COMMA);}
    public int countCOMMA() {return count(ASTNodeType.COMMA);}
    public ASTNode COMMA(int index) {return get(ASTNodeType.COMMA, index);}
    public ASTNode COMMA() {return COMMA(0);}

    public boolean hasSEMICOLON() {return has(ASTNodeType.SEMICOLON);}
    public int countSEMICOLON() {return count(ASTNodeType.SEMICOLON);}
    public ASTNode SEMICOLON(int index) {return get(ASTNodeType.SEMICOLON, index);}
    public ASTNode SEMICOLON() {return SEMICOLON(0);}

    public boolean hasASSIGN() {return has(ASTNodeType.ASSIGN);}
    public int countASSIGN() {return count(ASTNodeType.ASSIGN);}
    public ASTNode ASSIGN(int index) {return get(ASTNodeType.ASSIGN, index);}
    public ASTNode ASSIGN() {return ASSIGN(0);}

    public boolean hasBRACE_L_PLUS() {return has(ASTNodeType.BRACE_L_PLUS);}
    public int countBRACE_L_PLUS() {return count(ASTNodeType.BRACE_L_PLUS);}
    public ASTNode BRACE_L_PLUS(int index) {return get(ASTNodeType.BRACE_L_PLUS, index);}
    public ASTNode BRACE_L_PLUS() {return BRACE_L_PLUS(0);}

    public boolean hasBRACE_R_PLUS() {return has(ASTNodeType.BRACE_R_PLUS);}
    public int countBRACE_R_PLUS() {return count(ASTNodeType.BRACE_R_PLUS);}
    public ASTNode BRACE_R_PLUS(int index) {return get(ASTNodeType.BRACE_R_PLUS, index);}
    public ASTNode BRACE_R_PLUS() {return BRACE_R_PLUS(0);}

    public boolean hasBRACE_L_MEDIUM() {return has(ASTNodeType.BRACE_L_MEDIUM);}
    public int countBRACE_L_MEDIUM() {return count(ASTNodeType.BRACE_L_MEDIUM);}
    public ASTNode BRACE_L_MEDIUM(int index) {return get(ASTNodeType.BRACE_L_MEDIUM, index);}
    public ASTNode BRACE_L_MEDIUM() {return BRACE_L_MEDIUM(0);}

    public boolean hasBRACE_R_MEDIUM() {return has(ASTNodeType.BRACE_R_MEDIUM);}
    public int countBRACE_R_MEDIUM() {return count(ASTNodeType.BRACE_R_MEDIUM);}
    public ASTNode BRACE_R_MEDIUM(int index) {return get(ASTNodeType.BRACE_R_MEDIUM, index);}
    public ASTNode BRACE_R_MEDIUM() {return BRACE_R_MEDIUM(0);}

    public boolean hasBRACE_L() {return has(ASTNodeType.BRACE_L);}
    public int countBRACE_L() {return count(ASTNodeType.BRACE_L);}
    public ASTNode BRACE_L(int index) {return get(ASTNodeType.BRACE_L, index);}
    public ASTNode BRACE_L() {return BRACE_L(0);}

    public boolean hasBRACE_R() {return has(ASTNodeType.BRACE_R);}
    public int countBRACE_R() {return count(ASTNodeType.BRACE_R);}
    public ASTNode BRACE_R(int index) {return get(ASTNodeType.BRACE_R, index);}
    public ASTNode BRACE_R() {return BRACE_R(0);}

    public boolean hasPLUS() {return has(ASTNodeType.PLUS);}
    public int countPLUS() {return count(ASTNodeType.PLUS);}
    public ASTNode PLUS(int index) {return get(ASTNodeType.PLUS, index);}
    public ASTNode PLUS() {return PLUS(0);}

    public boolean hasMINUS() {return has(ASTNodeType.MINUS);}
    public int countMINUS() {return count(ASTNodeType.MINUS);}
    public ASTNode MINUS(int index) {return get(ASTNodeType.MINUS, index);}
    public ASTNode MINUS() {return MINUS(0);}

    public boolean hasMUL() {return has(ASTNodeType.MUL);}
    public int countMUL() {return count(ASTNodeType.MUL);}
    public ASTNode MUL(int index) {return get(ASTNodeType.MUL, index);}
    public ASTNode MUL() {return MUL(0);}

    public boolean hasDIV() {return has(ASTNodeType.DIV);}
    public int countDIV() {return count(ASTNodeType.DIV);}
    public ASTNode DIV(int index) {return get(ASTNodeType.DIV, index);}
    public ASTNode DIV() {return DIV(0);}

    public boolean hasNOT() {return has(ASTNodeType.NOT);}
    public int countNOT() {return count(ASTNodeType.NOT);}
    public ASTNode NOT(int index) {return get(ASTNodeType.NOT, index);}
    public ASTNode NOT() {return NOT(0);}

    public boolean hasMOD() {return has(ASTNodeType.MOD);}
    public int countMOD() {return count(ASTNodeType.MOD);}
    public ASTNode MOD(int index) {return get(ASTNodeType.MOD, index);}
    public ASTNode MOD() {return MOD(0);}

    public boolean hasGT() {return has(ASTNodeType.GT);}
    public int countGT() {return count(ASTNodeType.GT);}
    public ASTNode GT(int index) {return get(ASTNodeType.GT, index);}
    public ASTNode GT() {return GT(0);}

    public boolean hasLT() {return has(ASTNodeType.LT);}
    public int countLT() {return count(ASTNodeType.LT);}
    public ASTNode LT(int index) {return get(ASTNodeType.LT, index);}
    public ASTNode LT() {return LT(0);}

    public boolean hasGT_EQ() {return has(ASTNodeType.GT_EQ);}
    public int countGT_EQ() {return count(ASTNodeType.GT_EQ);}
    public ASTNode GT_EQ(int index) {return get(ASTNodeType.GT_EQ, index);}
    public ASTNode GT_EQ() {return GT_EQ(0);}

    public boolean hasLT_EQ() {return has(ASTNodeType.LT_EQ);}
    public int countLT_EQ() {return count(ASTNodeType.LT_EQ);}
    public ASTNode LT_EQ(int index) {return get(ASTNodeType.LT_EQ, index);}
    public ASTNode LT_EQ() {return LT_EQ(0);}

    public boolean hasEQ() {return has(ASTNodeType.EQ);}
    public int countEQ() {return count(ASTNodeType.EQ);}
    public ASTNode EQ(int index) {return get(ASTNodeType.EQ, index);}
    public ASTNode EQ() {return EQ(0);}

    public boolean hasNOT_EQ() {return has(ASTNodeType.NOT_EQ);}
    public int countNOT_EQ() {return count(ASTNodeType.NOT_EQ);}
    public ASTNode NOT_EQ(int index) {return get(ASTNodeType.NOT_EQ, index);}
    public ASTNode NOT_EQ() {return NOT_EQ(0);}

    public boolean hasAND() {return has(ASTNodeType.AND);}
    public int countAND() {return count(ASTNodeType.AND);}
    public ASTNode AND(int index) {return get(ASTNodeType.AND, index);}
    public ASTNode AND() {return AND(0);}

    public boolean hasOR() {return has(ASTNodeType.OR);}
    public int countOR() {return count(ASTNodeType.OR);}
    public ASTNode OR(int index) {return get(ASTNodeType.OR, index);}
    public ASTNode OR() {return OR(0);}

    public boolean hasCompUnit() {return has(ASTNodeType.compUnit);}
    public int countCompUnit() {return count(ASTNodeType.compUnit);}
    public ASTNode compUnit(int index) {return get(ASTNodeType.compUnit, index);}
    public ASTNode compUnit() {return compUnit(0);}

    public boolean hasDecl() {return has(ASTNodeType.decl);}
    public int countDecl() {return count(ASTNodeType.decl);}
    public ASTNode decl(int index) {return get(ASTNodeType.decl, index);}
    public ASTNode decl() {return decl(0);}

    public boolean hasConstDecl() {return has(ASTNodeType.constDecl);}
    public int countConstDecl() {return count(ASTNodeType.constDecl);}
    public ASTNode constDecl(int index) {return get(ASTNodeType.constDecl, index);}
    public ASTNode constDecl() {return constDecl(0);}

    public boolean hasBType() {return has(ASTNodeType.BType);}
    public int countBType() {return count(ASTNodeType.BType);}
    public ASTNode BType(int index) {return get(ASTNodeType.BType, index);}
    public ASTNode BType() {return BType(0);}

    public boolean hasConstDef() {return has(ASTNodeType.constDef);}
    public int countConstDef() {return count(ASTNodeType.constDef);}
    public ASTNode constDef(int index) {return get(ASTNodeType.constDef, index);}
    public ASTNode constDef() {return constDef(0);}

    public boolean hasConstInitial() {return has(ASTNodeType.constInitial);}
    public int countConstInitial() {return count(ASTNodeType.constInitial);}
    public ASTNode constInitial(int index) {return get(ASTNodeType.constInitial, index);}
    public ASTNode constInitial() {return constInitial(0);}

    public boolean hasVarDecl() {return has(ASTNodeType.varDecl);}
    public int countVarDecl() {return count(ASTNodeType.varDecl);}
    public ASTNode varDecl(int index) {return get(ASTNodeType.varDecl, index);}
    public ASTNode varDecl() {return varDecl(0);}

    public boolean hasVarDef() {return has(ASTNodeType.varDef);}
    public int countVarDef() {return count(ASTNodeType.varDef);}
    public ASTNode varDef(int index) {return get(ASTNodeType.varDef, index);}
    public ASTNode varDef() {return varDef(0);}

    public boolean hasInitVal() {return has(ASTNodeType.initVal);}
    public int countInitVal() {return count(ASTNodeType.initVal);}
    public ASTNode initVal(int index) {return get(ASTNodeType.initVal, index);}
    public ASTNode initVal() {return initVal(0);}

    public boolean hasFuncDef() {return has(ASTNodeType.funcDef);}
    public int countFuncDef() {return count(ASTNodeType.funcDef);}
    public ASTNode funcDef(int index) {return get(ASTNodeType.funcDef, index);}
    public ASTNode funcDef() {return funcDef(0);}

    public boolean hasFuncType() {return has(ASTNodeType.funcType);}
    public int countFuncType() {return count(ASTNodeType.funcType);}
    public ASTNode funcType(int index) {return get(ASTNodeType.funcType, index);}
    public ASTNode funcType() {return funcType(0);}

    public boolean hasFuncFParams() {return has(ASTNodeType.funcFParams);}
    public int countFuncFParams() {return count(ASTNodeType.funcFParams);}
    public ASTNode funcFParams(int index) {return get(ASTNodeType.funcFParams, index);}
    public ASTNode funcFParams() {return funcFParams(0);}

    public boolean hasFuncFParam() {return has(ASTNodeType.funcFParam);}
    public int countFuncFParam() {return count(ASTNodeType.funcFParam);}
    public ASTNode funcFParam(int index) {return get(ASTNodeType.funcFParam, index);}
    public ASTNode funcFParam() {return funcFParam(0);}

    public boolean hasBlock() {return has(ASTNodeType.block);}
    public int countBlock() {return count(ASTNodeType.block);}
    public ASTNode block(int index) {return get(ASTNodeType.block, index);}
    public ASTNode block() {return block(0);}

    public boolean hasBlockItem() {return has(ASTNodeType.blockItem);}
    public int countBlockItem() {return count(ASTNodeType.blockItem);}
    public ASTNode blockItem(int index) {return get(ASTNodeType.blockItem, index);}
    public ASTNode blockItem() {return blockItem(0);}

    public boolean hasStmt() {return has(ASTNodeType.stmt);}
    public int countStmt() {return count(ASTNodeType.stmt);}
    public ASTNode stmt(int index) {return get(ASTNodeType.stmt, index);}
    public ASTNode stmt() {return stmt(0);}

    public boolean hasExp() {return has(ASTNodeType.exp);}
    public int countExp() {return count(ASTNodeType.exp);}
    public ASTNode exp(int index) {return get(ASTNodeType.exp, index);}
    public ASTNode exp() {return exp(0);}

    public boolean hasCond() {return has(ASTNodeType.cond);}
    public int countCond() {return count(ASTNodeType.cond);}
    public ASTNode cond(int index) {return get(ASTNodeType.cond, index);}
    public ASTNode cond() {return cond(0);}

    public boolean hasLVal() {return has(ASTNodeType.lVal);}
    public int countLVal() {return count(ASTNodeType.lVal);}
    public ASTNode lVal(int index) {return get(ASTNodeType.lVal, index);}
    public ASTNode lVal() {return lVal(0);}

    public boolean hasPrimaryExp() {return has(ASTNodeType.primaryExp);}
    public int countPrimaryExp() {return count(ASTNodeType.primaryExp);}
    public ASTNode primaryExp(int index) {return get(ASTNodeType.primaryExp, index);}
    public ASTNode primaryExp() {return primaryExp(0);}

    public boolean hasUnaryExp() {return has(ASTNodeType.unaryExp);}
    public int countUnaryExp() {return count(ASTNodeType.unaryExp);}
    public ASTNode unaryExp(int index) {return get(ASTNodeType.unaryExp, index);}
    public ASTNode unaryExp() {return unaryExp(0);}

    public boolean hasUnaryOp() {return has(ASTNodeType.unaryOp);}
    public int countUnaryOp() {return count(ASTNodeType.unaryOp);}
    public ASTNode unaryOp(int index) {return get(ASTNodeType.unaryOp, index);}
    public ASTNode unaryOp() {return unaryOp(0);}

    public boolean hasFuncRParams() {return has(ASTNodeType.funcRParams);}
    public int countFuncRParams() {return count(ASTNodeType.funcRParams);}
    public ASTNode funcRParams(int index) {return get(ASTNodeType.funcRParams, index);}
    public ASTNode funcRParams() {return funcRParams(0);}

    public boolean hasMulExp() {return has(ASTNodeType.mulExp);}
    public int countMulExp() {return count(ASTNodeType.mulExp);}
    public ASTNode mulExp(int index) {return get(ASTNodeType.mulExp, index);}
    public ASTNode mulExp() {return mulExp(0);}

    public boolean hasAddExp() {return has(ASTNodeType.addExp);}
    public int countAddExp() {return count(ASTNodeType.addExp);}
    public ASTNode addExp(int index) {return get(ASTNodeType.addExp, index);}
    public ASTNode addExp() {return addExp(0);}

    public boolean hasRelExp() {return has(ASTNodeType.relExp);}
    public int countRelExp() {return count(ASTNodeType.relExp);}
    public ASTNode relExp(int index) {return get(ASTNodeType.relExp, index);}
    public ASTNode relExp() {return relExp(0);}

    public boolean hasEqExp() {return has(ASTNodeType.eqExp);}
    public int countEqExp() {return count(ASTNodeType.eqExp);}
    public ASTNode eqExp(int index) {return get(ASTNodeType.eqExp, index);}
    public ASTNode eqExp() {return eqExp(0);}

    public boolean hasLAndExp() {return has(ASTNodeType.lAndExp);}
    public int countLAndExp() {return count(ASTNodeType.lAndExp);}
    public ASTNode lAndExp(int index) {return get(ASTNodeType.lAndExp, index);}
    public ASTNode lAndExp() {return lAndExp(0);}

    public boolean hasLOrExp() {return has(ASTNodeType.lOrExp);}
    public int countLOrExp() {return count(ASTNodeType.lOrExp);}
    public ASTNode lOrExp(int index) {return get(ASTNodeType.lOrExp, index);}
    public ASTNode lOrExp() {return lOrExp(0);}

    public boolean hasConstExp() {return has(ASTNodeType.constExp);}
    public int countConstExp() {return count(ASTNodeType.constExp);}
    public ASTNode constExp(int index) {return get(ASTNodeType.constExp, index);}
    public ASTNode constExp() {return constExp(0);}
}
