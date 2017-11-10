package cn.sinjinsong.syntaxanalyzer.enumeration;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/10/23.
 */
public enum Token {
    //错误
    ERROR(-1, "error"),
    //常数
    DIGIT(0, "digit"),
    //关键字们
    ABSTRACT(1, "abstract"),
    BOOLEAN(2, "boolean"),
    BREAK(3, "break"),
    BYTE(4, "byte"),
    CASE(5, "case"),
    CATCH(6, "catch"),
    CHAR(7, "char"),
    CLASS(8, "class"),
    CONTINUE(9, "continue"),
    DEFAULT(10, "default"),
    DO(11, "do"),
    DOUBLE(12, "double"),
    ELSE(13, "else"),
    EXTENDS(14, "extends"),
    FINAL(15, "final"),
    FINALLY(16, "finally"),
    FLOAT(17, "float"),
    FOR(18, "for"),
    IF(19, "if"),
    IMPLEMENTS(20, "implements"),
    IMPORT(21, "import"),
    INSTANCEOF(22, "instanceof"),
    INT(23, "int"),
    INTERFACE(24, "interface"),
    LONG(25, "long"),
    NATIVE(26, "native"),
    NEW(27, "new"),
    PACKAGE(28, "package"),
    PRIVATE(29, "private"),
    PROTECTED(30, "protected"),
    PUBLIC(31, "public"),
    RETURN(32, "return"),
    SHORT(33, "short"),
    STATIC(34, "static"),
    SUPER(35, "super"),
    SWITCH(36, "switch"),
    SYNCHRONIZED(37, "synchronized"),
    THIS(38, "this"),
    THROW(39, "throw"),
    THROWS(40, "throws"),
    TRANSIENT(41, "transient"),
    TRY(42, "try"),
    VOID(43, "void"),
    VOLATILE(44, "volatile"),
    WHILE(45, "while"),
    ASSERT(46, "assert"),
    CONST(47, "const"),
    ENUM(48, "enum"),
    GOTO(49, "goto"),
    STRICTFP(50, "strictfp"),
    //运算符们
    PLUS(51, "+"),
    MINUS(52, "-"),
    MUL(53, "*"),
    DIV(54, "/"),
    AND(55, "&"),
    OR(56, "|"),
    NOT(57, "!"),
    EQ(58, "="),
    LT(59, "<"),
    GT(60, ">"),
    //比较运算符们
    GT_EQ(61,">="),
    LT_EQ(62,"<="),
    IS_EQ(63,"=="),
    NOT_EQ(64,"!="),
    //界符们
    COMMA(65, ","),
    SEMICOLON(66, ";"),
    LEFT_BRACE(67, "{"),
    RIGHT_BRACE(68, "}"),
    LEFT_PARENTHESES(69, "("),
    RIGHT_PARENTHESES(70, ")"),
    LEFT_BRACKET(71, "["),
    RIGHT_BRACKET(72, "]"),
    UNDERSCORE(73, "_"),
    COLON(74, ":"),
    DOT(75, "."),
    QUOTE(76, "\""),
    
    //标识符们
    IDENTIFIER(77, "identifier"),
    //非终结符们
    END(100,"$"),
    PROGRAM(101,"Program"),
    FUNC_BLOCK(102,"FuncBlock"),
    DATA_TYPE(103,"DataType"),
    FUNC_NAME(104,"FuncName"),
    PARAMS(105,"Params"),
    PARAM(106,"Param"),
    STATEMENTS(107,"Statements"),
    S(108,"S"),
    PICK_VALUE(109,"PickValue"),
    BLOCK(110,"Block"),
    CONDITION(111,"Condition"),
    KEYWORD(112,"Keyword"),
    RETURN_CLAUSE(113,"ReturnClause"),
    COMPARE_OPERATOR(114,"CompareOperator");
    public int categoryCode;
    public String symbol;

    private static Map<String, Token> SYMBOL_TO_TOKEN = new HashMap<>();

    Token(int categoryCode, String symbol) {
        this.categoryCode = categoryCode;
        this.symbol = symbol;

    }

    static {
        for (Token token : values()) {
            SYMBOL_TO_TOKEN.put(token.symbol, token);
        }
    }
    
    public static Token getBySymbol(String symbol) {
        return SYMBOL_TO_TOKEN.get(symbol);
    }
}
