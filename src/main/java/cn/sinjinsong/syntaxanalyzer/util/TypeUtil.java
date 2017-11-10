package cn.sinjinsong.syntaxanalyzer.util;

/**
 * Created by SinjinSong on 2017/10/23.
 */
public class TypeUtil {
    private static final String KEYWORDS[] = {"abstract", "boolean", "break", "byte",
            "case", "catch", "char", "class", "continue", "default", "do",
            "double", "else", "extends", "final", "finally", "float", "for",
            "if", "implements", "import", "instanceof", "int", "interface",
            "long", "native", "new", "package", "private", "protected",
            "public", "return", "short", "static", "super", "switch",
            "synchronized", "this", "throw", "throws", "transient", "try",
            "void", "volatile", "while", "strictfp", "enum", "goto", "const", "assert"}; // 关键字数组
    private static final char OPERATORS[] = {'+', '-', '*', '/', '=', '>', '<', '&', '|',
            '!'}; // 运算符数组
    private static final char SEPARATORS[] = {',', ';', '{', '}', '(', ')', '[', ']', '_',
            ':', '.', '"'}; // 界符数组

    /**
     * 是否是字母
     * @param ch
     * @return
     */
    public static boolean isLetter(char ch) {
        return Character.isLetter(ch);
    }

    /**
     * 是否是数字
     * @param ch
     * @return
     */
    public static boolean isDigit(char ch) {
        return Character.isDigit(ch);
    }

    /**
     * 是否是运算符
     * @param ch
     * @return
     */
    public static boolean isOperator(char ch) {
        for (int i = 0; i < OPERATORS.length; ++i) {
            if (ch == OPERATORS[i]) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是界符
     * @param ch
     * @return
     */
    public static boolean isSeparators(char ch){
        for (int i = 0; i < SEPARATORS.length; i++) {
            if(ch == SEPARATORS[i]){
                return true;
            }
        }
        return false;
    }

    /**
     * 是否是关键字
     * @param str
     * @return
     */
    public static boolean isKeyWord(String str){
        for (int i = 0; i < KEYWORDS.length; i++) {
            if(str.equals(KEYWORDS[i])){
                return true;
            }
        }
        return false;
    }
    
    
    
    

}
