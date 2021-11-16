public enum TokenType {
    ident, number, decNumber, octNumber, hexNumber,
    braceL, braceR, braceLPlus, braceRPlus, braceLMedium, braceRMedium,
    semicolon, assignment, equal, notEqual, comma,
    add, mns, div, mul, mod, not,
    gt, gteq, lt, lteq, and, or,

    INT, MAIN, RETURN, CONST, VOID, IF, ELSE, WHILE, BREAK, CONTINUE,
    WHITE, COMMENT,
}
