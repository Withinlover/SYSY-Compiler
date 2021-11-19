import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    private final ArrayList<Token> tokens;
    private int pos = 0;

    public Parser(ArrayList<Token> tokens) {
        this.tokens = tokens;
    }

    private TokenType getTokensType(int index) {
        if (index >= tokens.size())
            return null;
        return tokens.get(index).getType();
    }
    private TokenType getTokensType() {
        return getTokensType(pos);
    }

    private Void checkTokenType(TokenType type) throws ParseException{
        if (getTokensType(pos) != type)
            throw new ParseException("At pos = " + pos + " should be: " + type.name());
        return null;
    }

    public ASTNode getRoot() {
        pos = 0; ASTNode res = null;
        try {
            res = parseCompUnit();
        } catch (ParseException e) {
            System.out.println(e.toString());
            System.exit(-1);
        }
        return res;
    }

    private ASTNode parseNumber() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.number);
        if (getTokensType(pos) != TokenType.hexNumber &&
                getTokensType(pos) != TokenType.octNumber &&
                getTokensType(pos) != TokenType.decNumber)
            throw new ParseException("at pos = " + pos + " 此处应当是一个数字");

        switch (getTokensType()) {
            case decNumber -> {
                int num = Integer.parseInt(tokens.get(pos).getText());
                res.setValue(num);
            }
            case octNumber -> {
                String str = tokens.get(pos).getText();
                int num = 0, len = str.length();
                for (int i = 1; i < len; ++i)
                    num = num * 8 + (str.charAt(i) - '0');
                res.setValue(num);
            }
            case hexNumber -> {
                String str = tokens.get(pos).getText().toLowerCase();
                int num = 0, len = str.length();
                String tmp = "0123456789abcdef";
                for (int i = 2; i < len; ++i)
                    num = num * 16 + tmp.indexOf(str.charAt(i));
                res.setValue(num);
            }
        }
        pos++;
        return res;
    }

    private ASTNode parseIdent() throws ParseException {
        checkTokenType(TokenType.ident);
        ASTNode res = new ASTNode(ASTNodeType.ident);
        res.setToken(tokens.get(pos));
        res.setName(tokens.get(pos).getName());
        pos = pos + 1;
        return res;
    }

    private ASTNode parseCONST() throws ParseException {
        checkTokenType(TokenType.CONST);
        ASTNode res = new ASTNode(ASTNodeType.CONST);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseINT() throws ParseException {
        checkTokenType(TokenType.INT);
        ASTNode res = new ASTNode(ASTNodeType.INT);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseVOID() throws ParseException {
        checkTokenType(TokenType.VOID);
        ASTNode res = new ASTNode(ASTNodeType.VOID);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseIF() throws ParseException {
        checkTokenType(TokenType.IF);
        ASTNode res = new ASTNode(ASTNodeType.IF);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseELSE() throws ParseException {
        checkTokenType(TokenType.ELSE);
        ASTNode res = new ASTNode(ASTNodeType.ELSE);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseWHILE() throws ParseException {
        checkTokenType(TokenType.WHILE);
        ASTNode res = new ASTNode(ASTNodeType.WHILE);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseBREAK() throws ParseException {
        checkTokenType(TokenType.BREAK);
        ASTNode res = new ASTNode(ASTNodeType.BREAK);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseCONTINUE() throws ParseException {
        checkTokenType(TokenType.CONTINUE);
        ASTNode res = new ASTNode(ASTNodeType.CONTINUE);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseRETURN() throws ParseException {
        checkTokenType(TokenType.RETURN);
        ASTNode res = new ASTNode(ASTNodeType.RETURN);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseCOMMA() throws ParseException {
        checkTokenType(TokenType.comma);
        ASTNode res = new ASTNode(ASTNodeType.COMMA);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseSEMICOLON() throws ParseException {
        checkTokenType(TokenType.semicolon);
        ASTNode res = new ASTNode(ASTNodeType.SEMICOLON);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseASSIGN() throws ParseException {
        checkTokenType(TokenType.assignment);
        ASTNode res = new ASTNode(ASTNodeType.ASSIGN);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseBRACE_L_PLUS() throws ParseException {
        checkTokenType(TokenType.braceLPlus);
        ASTNode res = new ASTNode(ASTNodeType.BRACE_L_PLUS);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseBRACE_R_PLUS() throws ParseException {
        checkTokenType(TokenType.braceRPlus);
        ASTNode res = new ASTNode(ASTNodeType.BRACE_R_PLUS);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseBRACE_L_MEDIUM() throws ParseException {
        checkTokenType(TokenType.braceLMedium);
        ASTNode res = new ASTNode(ASTNodeType.BRACE_L_MEDIUM);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseBRACE_R_MEDIUM() throws ParseException {
        checkTokenType(TokenType.braceRMedium);
        ASTNode res = new ASTNode(ASTNodeType.BRACE_R_MEDIUM);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseBRACE_L() throws ParseException {
        checkTokenType(TokenType.braceL);
        ASTNode res = new ASTNode(ASTNodeType.BRACE_L);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseBRACE_R() throws ParseException {
        checkTokenType(TokenType.braceR);
        ASTNode res = new ASTNode(ASTNodeType.BRACE_R);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parsePLUS() throws ParseException {
        checkTokenType(TokenType.add);
        ASTNode res = new ASTNode(ASTNodeType.PLUS);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseMINUS() throws ParseException {
        checkTokenType(TokenType.mns);
        ASTNode res = new ASTNode(ASTNodeType.MINUS);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseMUL() throws ParseException {
        checkTokenType(TokenType.mul);
        ASTNode res = new ASTNode(ASTNodeType.MUL);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseDIV() throws ParseException {
        checkTokenType(TokenType.div);
        ASTNode res = new ASTNode(ASTNodeType.DIV);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseNOT() throws ParseException {
        checkTokenType(TokenType.not);
        ASTNode res = new ASTNode(ASTNodeType.NOT);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseMOD() throws ParseException {
        checkTokenType(TokenType.mod);
        ASTNode res = new ASTNode(ASTNodeType.MOD);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseGT() throws ParseException {
        checkTokenType(TokenType.gt);
        ASTNode res = new ASTNode(ASTNodeType.GT);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseLT() throws ParseException {
        checkTokenType(TokenType.lt);
        ASTNode res = new ASTNode(ASTNodeType.LT);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseGT_EQ() throws ParseException {
        checkTokenType(TokenType.gteq);
        ASTNode res = new ASTNode(ASTNodeType.GT_EQ);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseLT_EQ() throws ParseException {
        checkTokenType(TokenType.lteq);
        ASTNode res = new ASTNode(ASTNodeType.LT_EQ);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseEQ() throws ParseException {
        checkTokenType(TokenType.equal);
        ASTNode res = new ASTNode(ASTNodeType.EQ);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseNOT_EQ() throws ParseException {
        checkTokenType(TokenType.notEqual);
        ASTNode res = new ASTNode(ASTNodeType.NOT_EQ);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseAND() throws ParseException {
        checkTokenType(TokenType.and);
        ASTNode res = new ASTNode(ASTNodeType.AND);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseOR() throws ParseException {
        checkTokenType(TokenType.or);
        ASTNode res = new ASTNode(ASTNodeType.OR);
        res.setToken(tokens.get(pos));
        pos = pos + 1;
        return res;
    }

    private ASTNode parseCompUnit() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.compUnit);
        switch (getTokensType()) {
            case CONST -> res.addChild(parseDecl());
            case VOID -> res.addChild(parseFuncDef());
            case INT -> {
                if (getTokensType(pos + 2) == TokenType.braceL) {
                    res.addChild(parseFuncDef());
                } else {
                    res.addChild(parseDecl());
                }
            }
            default -> throw new ParseException("Parse compUnit, Should be \"const\" , \"int\" , \"void\" ");
        }
        if (getTokensType() != null) res.addChild(parseCompUnit());
        return res;
    }

    private ASTNode parseDecl() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.decl);
        switch (getTokensType()) {
            case CONST -> res.addChild(parseConstDecl());
            case INT -> res.addChild(parseVarDecl());
            default -> throw new ParseException("Parse Decl, Should be \"const\" or \"int\" ");
        }
        return res;
    }

    private ASTNode parseConstDecl() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.constDecl);
        res.addChild(parseCONST());
        res.addChild(parseBType());
        res.addChild(parseConstDef());

        while (getTokensType() == TokenType.comma) {
            res.addChild(parseCOMMA());
            res.addChild(parseConstDef());
        }

        res.addChild(parseSEMICOLON());
        return res;
    }

    private ASTNode parseBType() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.BType);
        res.addChild(parseINT());
        return res;
    }

    private ASTNode parseConstDef() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.constDef);
        res.addChild(parseIdent());
        while (getTokensType() == TokenType.braceLMedium) {
            res.addChild(parseBRACE_L_MEDIUM());
            res.addChild(parseConstExp());
            res.addChild(parseBRACE_R_MEDIUM());
        }
        res.addChild(parseASSIGN());
        res.addChild(parseConstInitial());
        return res;
    }

    private ASTNode parseConstInitial() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.constInitial);
        if (getTokensType() == TokenType.braceLPlus) {
            res.addChild(parseBRACE_L_PLUS());
            if (getTokensType() != TokenType.braceRPlus) {
                res.addChild(parseConstInitial());
                while (getTokensType() == TokenType.comma) {
                    res.addChild(parseCOMMA());
                    res.addChild(parseConstInitial());
                }
            }
            res.addChild(parseBRACE_R_PLUS());
        } else {
            res.addChild(parseConstExp());
        }
        return res;
    }

    private ASTNode parseVarDecl() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.varDecl);
        res.addChild(parseBType());
        res.addChild(parseVarDef());
        while (getTokensType() == TokenType.comma) {
            res.addChild(parseCOMMA());
            res.addChild(parseVarDef());
        }
        res.addChild(parseSEMICOLON());
        return res;
    }

    private ASTNode parseVarDef() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.varDef);
        res.addChild(parseIdent());
        while (getTokensType() == TokenType.braceLMedium) {
            res.addChild(parseBRACE_L_MEDIUM());
            res.addChild(parseConstExp());
            res.addChild(parseBRACE_R_MEDIUM());
        }
        if (getTokensType() == TokenType.assignment) {
            res.addChild(parseASSIGN());
            res.addChild(parseInitVal());
        }
        return res;
    }

    private ASTNode parseInitVal() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.initVal);
        if (getTokensType() == TokenType.braceLPlus) {
            res.addChild(parseBRACE_L_PLUS());
            if (getTokensType() != TokenType.braceRPlus) {
                res.addChild(parseInitVal());
                while (getTokensType() == TokenType.comma) {
                    res.addChild(parseCOMMA());
                    res.addChild(parseInitVal());
                }
            }
            res.addChild(parseBRACE_R_PLUS());
        } else {
            res.addChild(parseExp());
        }
        return res;
    }

    private ASTNode parseFuncDef() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.funcDef);
        res.addChild(parseFuncType());
        res.addChild(parseIdent());
        res.addChild(parseBRACE_L());
        if (getTokensType() != TokenType.braceR)
            res.addChild(parseFuncFParams());
        res.addChild(parseBRACE_R());
        res.addChild(parseBlock());
        return res;
    }

    private ASTNode parseFuncType() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.funcType);
        if (getTokensType() == TokenType.VOID)
            res.addChild(parseVOID());
        else
            res.addChild(parseINT());
        return res;
    }

    private ASTNode parseFuncFParams() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.funcFParams);
        res.addChild(parseFuncFParam());
        while (getTokensType() == TokenType.comma) {
            res.addChild(parseCOMMA());
            res.addChild(parseFuncFParam());
        }
        return res;
    }

    private ASTNode parseFuncFParam() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.funcFParam);
        res.addChild(parseBType());
        res.addChild(parseIdent());
        if (getTokensType() == TokenType.braceLMedium) {
            res.addChild(parseBRACE_L_MEDIUM());
            res.addChild(parseBRACE_R_MEDIUM());
            while (getTokensType() == TokenType.braceLMedium) {
                res.addChild(parseBRACE_L_MEDIUM());
                res.addChild(parseExp());
                res.addChild(parseBRACE_L_MEDIUM());
            }
        }
        return res;
    }

    private ASTNode parseBlock() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.block);
        res.addChild(parseBRACE_L_PLUS());
        while (getTokensType() != TokenType.braceRPlus) {
            res.addChild(parseBlockItem());
        }
        res.addChild(parseBRACE_R_PLUS());
        return res;
    }

    private ASTNode parseBlockItem() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.blockItem);
        if (getTokensType() == TokenType.CONST || getTokensType() == TokenType.INT) {
            res.addChild(parseDecl());
        } else {
            res.addChild(parseStmt());
        }
        return res;
    }

    private ASTNode parseStmt() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.stmt);
        switch (getTokensType()) {
            case IF -> {
                res.addChild(parseIF());
                res.addChild(parseBRACE_L());
                res.addChild(parseCond());
                res.addChild(parseBRACE_R());
                res.addChild(parseStmt());
                if (getTokensType() == TokenType.ELSE) {
                    res.addChild(parseELSE());
                    res.addChild(parseStmt());
                }
            }
            case WHILE -> {
                res.addChild(parseWHILE());
                res.addChild(parseBRACE_L());
                res.addChild(parseCond());
                res.addChild(parseBRACE_R());
                res.addChild(parseStmt());
            }
            case BREAK -> {
                res.addChild(parseBREAK());
                res.addChild(parseSEMICOLON());
            }
            case CONTINUE -> {
                res.addChild(parseCONTINUE());
                res.addChild(parseSEMICOLON());
            }
            case RETURN -> {
                res.addChild(parseRETURN());
                if (getTokensType() != TokenType.semicolon)
                    res.addChild(parseExp());
                res.addChild(parseSEMICOLON());
            }
            case braceLPlus -> {
                res.addChild(parseBlock());
            }
            case ident -> {
                if (getTokensType(pos + 1) == TokenType.braceLMedium
                    || getTokensType(pos + 1) == TokenType.assignment) {
                    res.addChild(parseLVal());
                    res.addChild(parseASSIGN());
                    res.addChild(parseExp());
                    res.addChild(parseSEMICOLON());
                } else {
                    if (getTokensType() != TokenType.semicolon)
                        res.addChild(parseExp());
                    res.addChild(parseSEMICOLON());
                }
            }
            default -> {
                if (getTokensType() != TokenType.semicolon)
                    res.addChild(parseExp());
                res.addChild(parseSEMICOLON());
            }
        }
        return res;
    }

    private ASTNode parseExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.exp);
        res.addChild(parseAddExp());
        return res;
    }

    private ASTNode parseCond() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.cond);
        res.addChild(parseLOrExp());
        return res;
    }

    private ASTNode parseLVal() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.lVal);
        res.addChild(parseIdent());
        while (getTokensType() == TokenType.braceLMedium) {
            res.addChild(parseBRACE_L_MEDIUM());
            res.addChild(parseExp());
            res.addChild(parseBRACE_R_MEDIUM());
        }
        return res;
    }

    private ASTNode parsePrimaryExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.primaryExp);
        switch (getTokensType()) {
            case braceL -> {
                res.addChild(parseBRACE_L());
                res.addChild(parseExp());
                res.addChild(parseBRACE_R());
            }
            case ident -> {
                res.addChild(parseLVal());
            }
            default -> {
                res.addChild(parseNumber());
            }
        }
        return res;
    }

    private ASTNode parseUnaryExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.unaryExp);
        switch (getTokensType()) {
            case ident -> {
                if (getTokensType(pos + 1) == TokenType.braceL) {
                    res.addChild(parseIdent());
                    res.addChild(parseBRACE_L());
                    if (getTokensType() != TokenType.braceR)
                        res.addChild(parseFuncRParams());
                    res.addChild(parseBRACE_R());
                } else {
                    res.addChild(parsePrimaryExp());
                }
            }
            case add, mns, not -> {
                res.addChild(parseUnaryOp());
                res.addChild(parseUnaryExp());
            }
            default -> res.addChild(parsePrimaryExp());
        }
        return res;
    }

    private ASTNode parseUnaryOp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.unaryOp);
        switch (getTokensType()) {
            case add -> res.addChild(parsePLUS());
            case mns -> res.addChild(parseMINUS());
            case not -> res.addChild(parseNOT());
            default -> throw new ParseException("should be +-!");
        }
        return res;
    }

    private ASTNode parseFuncRParams() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.funcRParams);
        res.addChild(parseExp());
        while (getTokensType() == TokenType.comma) {
            res.addChild(parseCOMMA());
            res.addChild(parseExp());
        }
        return res;
    }

    private ASTNode parseMulExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.mulExp);
        res.addChild(parseUnaryExp());
        while (getTokensType() == TokenType.mul
            || getTokensType() == TokenType.div
            || getTokensType() == TokenType.mod) {
            switch (getTokensType()) {
                case mul -> res.addChild(parseMUL());
                case div -> res.addChild(parseDIV());
                case mod -> res.addChild(parseMOD());
            }
            res.addChild(parseUnaryExp());
        }
        ArrayList<ASTNode> childs = res.getChilds();
        int len = childs.size();
        ASTNode trueRes = new ASTNode(ASTNodeType.mulExp);
        trueRes.addChild(childs.get(0));
        for (int i = 1;i < len;i += 2) {
            ASTNode tmp = new ASTNode(ASTNodeType.mulExp);
            tmp.addChild(trueRes);
            tmp.addChild(childs.get(i));
            tmp.addChild(childs.get(i + 1));
            trueRes = tmp;
        }
        return trueRes;
    }

    private ASTNode parseAddExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.addExp);
        res.addChild(parseMulExp());
        while (getTokensType() == TokenType.add
            || getTokensType() == TokenType.mns) {
            if (getTokensType() == TokenType.add)
                res.addChild(parsePLUS());
            else
                res.addChild(parseMINUS());
            res.addChild(parseMulExp());
        }
        ArrayList<ASTNode> childs = res.getChilds();
        int len = childs.size();
        ASTNode trueRes = new ASTNode(ASTNodeType.addExp);
        trueRes.addChild(childs.get(0));
        for (int i = 1;i < len;i += 2) {
            ASTNode tmp = new ASTNode(ASTNodeType.addExp);
            tmp.addChild(trueRes);
            tmp.addChild(childs.get(i));
            tmp.addChild(childs.get(i + 1));
            trueRes = tmp;
        }
        return trueRes;
    }

    private ASTNode parseRelExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.relExp);
        res.addChild(parseAddExp());
        while (getTokensType() == TokenType.lt
            || getTokensType() == TokenType.gt
            || getTokensType() == TokenType.lteq
            || getTokensType() == TokenType.gteq) {
            switch (getTokensType()) {
                case lt -> res.addChild(parseLT());
                case gt -> res.addChild(parseGT());
                case gteq -> res.addChild(parseGT_EQ());
                case lteq -> res.addChild(parseLT_EQ());
            }
            res.addChild(parseAddExp());
        }
        ArrayList<ASTNode> childs = res.getChilds();
        int len = childs.size();
        ASTNode trueRes = new ASTNode(ASTNodeType.relExp);
        trueRes.addChild(childs.get(0));
        for (int i = 1;i < len;i += 2) {
            ASTNode tmp = new ASTNode(ASTNodeType.relExp);
            tmp.addChild(trueRes);
            tmp.addChild(childs.get(i));
            tmp.addChild(childs.get(i + 1));
            trueRes = tmp;
        }
        return trueRes;
    }

    private ASTNode parseEqExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.eqExp);
        res.addChild(parseRelExp());
        while (getTokensType() == TokenType.equal
            || getTokensType() == TokenType.notEqual) {
            if (getTokensType() == TokenType.equal)
                res.addChild(parseEQ());
            else
                res.addChild(parseNOT_EQ());
            res.addChild(parseRelExp());
        }
        ArrayList<ASTNode> childs = res.getChilds();
        int len = childs.size();
        ASTNode trueRes = new ASTNode(ASTNodeType.eqExp);
        trueRes.addChild(childs.get(0));
        for (int i = 1;i < len;i += 2) {
            ASTNode tmp = new ASTNode(ASTNodeType.eqExp);
            tmp.addChild(trueRes);
            tmp.addChild(childs.get(i));
            tmp.addChild(childs.get(i + 1));
            trueRes = tmp;
        }
        return trueRes;
    }

    private ASTNode parseLAndExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.lAndExp);
        res.addChild(parseEqExp());
        while (getTokensType() == TokenType.and) {
            res.addChild(parseAND());
            res.addChild(parseEqExp());
        }
        ArrayList<ASTNode> childs = res.getChilds();
        int len = childs.size();
        ASTNode trueRes = new ASTNode(ASTNodeType.lAndExp);
        trueRes.addChild(childs.get(0));
        for (int i = 1;i < len;i += 2) {
            ASTNode tmp = new ASTNode(ASTNodeType.lAndExp);
            tmp.addChild(trueRes);
            tmp.addChild(childs.get(i));
            tmp.addChild(childs.get(i + 1));
            trueRes = tmp;
        }
        return trueRes;
    }

    private ASTNode parseLOrExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.lOrExp);
        res.addChild(parseLAndExp());
        while (getTokensType() == TokenType.or) {
            res.addChild(parseOR());
            res.addChild(parseLAndExp());
        }
        ArrayList<ASTNode> childs = res.getChilds();
        int len = childs.size();
        ASTNode trueRes = new ASTNode(ASTNodeType.lOrExp);
        trueRes.addChild(childs.get(0));
        for (int i = 1;i < len;i += 2) {
            ASTNode tmp = new ASTNode(ASTNodeType.lOrExp);
            tmp.addChild(trueRes);
            tmp.addChild(childs.get(i));
            tmp.addChild(childs.get(i + 1));
            trueRes = tmp;
        }
        return trueRes;
    }

    private ASTNode parseConstExp() throws ParseException {
        ASTNode res = new ASTNode(ASTNodeType.constExp);
        res.addChild(parseAddExp());
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
        Parser parser = new Parser(lexer.getTokens());
        ASTNode root = parser.getRoot();
        System.out.println (root.getType());
    }
}
