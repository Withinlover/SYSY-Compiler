# SYSY-Compiler
软件学院大三编译原理课程设计

## SYSY 语言简介

miniSysY 语言是在 SysY 语言 基础上进行一些修改得到的 C 语言子集。

编译原理实验课剩下的工作就是从编译一个简单的 main() 函数开始，逐渐扩充对 miniSysY 语言文法的支持，实现一个较为完整的编译器。

由于我们没有系统地学习过汇编语言，我们只需要将 miniSysY 代码编译到 LLVM IR。

## SYSY 文法

完整的 SYSY 文法如下：

```sysy
CompUnit     -> [CompUnit] (Decl | FuncDef)
Decl         -> ConstDecl | VarDecl
ConstDecl    -> 'const' BType ConstDef { ',' ConstDef } ';'
BType        -> 'int'
ConstDef     -> Ident { '[' ConstExp ']' } '=' ConstInitVal
ConstInitVal -> ConstExp 
                | '{' [ ConstInitVal { ',' ConstInitVal } ] '}'
VarDecl      -> BType VarDef { ',' VarDef } ';'
VarDef       -> Ident { '[' ConstExp ']' }
                | Ident { '[' ConstExp ']' } '=' InitVal
InitVal      -> Exp 
                | '{' [ InitVal { ',' InitVal } ] '}'
FuncDef      -> FuncType Ident '(' [FuncFParams] ')' Block
FuncType     -> 'void' | 'int'  
FuncFParams  -> FuncFParam { ',' FuncFParam }
FuncFParam   -> BType Ident ['[' ']' { '[' Exp ']' }]
Block        -> '{' { BlockItem } '}'
BlockItem    -> Decl | Stmt
Stmt         -> LVal '=' Exp ';' 
                | [Exp] ';' 
                | Block
                | 'if' '(' Cond ')' Stmt [ 'else' Stmt ]
                | 'while' '(' Cond ')' Stmt
                | 'break' ';' 
                | 'continue' ';'
                | 'return' [Exp] ';'
Exp          -> AddExp
Cond         -> LOrExp
LVal         -> Ident {'[' Exp ']'}
PrimaryExp   -> '(' Exp ')' | LVal | Number
UnaryExp     -> PrimaryExp 
                | Ident '(' [FuncRParams] ')'
                | UnaryOp UnaryExp
UnaryOp      -> '+' | '-' | '!'  // 注：保证 '!' 仅出现在 Cond 中
FuncRParams  -> Exp { ',' Exp }
MulExp       -> UnaryExp 
                | MulExp ('*' | '/' | '%') UnaryExp
AddExp       -> MulExp 
                | AddExp ('+' | '−') MulExp
RelExp       -> AddExp 
                | RelExp ('<' | '>' | '<=' | '>=') AddExp
EqExp        -> RelExp 
                | EqExp ('==' | '!=') RelExp
LAndExp      -> EqExp 
                | LAndExp '&&' EqExp
LOrExp       -> LAndExp 
                | LOrExp '||' LAndExp
ConstExp     -> AddExp  // 在语义上额外约束这里的 AddExp 必须是一个可以在编译期求出值的常量
```

文法细节补充：

注释：

- 单行注释：以 `//` 开始，直到换行符结束，不包括换行符。
- 多行注释：以 `/*` 开始，直到第一次出现 `*/` 时结束，包括 `*/`。

`Ident` 标识符：

```SYSY
Ident    -> Nondigit
            | Ident Nondigit
            | Ident Digit
Nondigit -> '_' | 'a' | 'b' | ... | 'z' | 'A' | 'B' | ... | 'Z'
Digit    -> '0' | '1' | ... | '9'
```

对于同名标识符的规定：

- 全局变量和局部变量的作用域可以重叠，重叠部分局部变量优先；
- 同名局部变量的作用域不能重叠；
- 变量名可以与函数名相同。

`Number` 数字：

```SYSY
Number             -> decimal-const | octal-const | hexadecimal-const
decimal-const      -> nonzero-digit | decimal-const digit
octal-const        -> '0' | octal-const octal-digit
hexadecimal-const  -> hexadecimal-prefix hexadecimal-digit 
                      | hexadecimal-const hexadecimal-digit
hexadecimal-prefix -> '0x' | '0X'
nonzero-digit      -> '1' | '2' | ... | '9'
octal-digit        -> '0' | '1' | ... | '7'
digit              -> '0' | nonzero-digit
hexadecimal-digit  -> '0' | '1' | ... | '9'
                      | 'a' | 'b' | 'c' | 'd' | 'e' | 'f'
                      | 'A' | 'B' | 'C' | 'D' | 'E' | 'F'
```

## 设计方法

咕咕咕