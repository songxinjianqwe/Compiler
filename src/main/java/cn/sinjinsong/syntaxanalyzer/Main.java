package cn.sinjinsong.syntaxanalyzer;

import cn.sinjinsong.syntaxanalyzer.lexer.LexerAnalyzer;
import cn.sinjinsong.syntaxanalyzer.syntax.SyntaxAnalyzer;
import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * Created by SinjinSong on 2017/11/10.
 * <p>
 * 流程：
 * 0）读入CFG
 * 1）初始化PPT
 * 2）遍历每个产生式，求右侧的First，若为ε，则求左侧的Follow
 * 3）将结果填到PPT中
 * <p>
 * 4）初始化分析栈，底部填入$和开始符
 * 5）处理输入串，末端添加$。遍历输入串，根据当前字符和分析栈顶，查询PPT，并将查得产生式右侧代替栈中的左侧（从右向左依次入栈），
 * 输出查得的产生式，直至两个$匹配。
 * 6）收集所有用到的产生式，输出推导序列
 * 7）结束
 */
public class Main {
    public static void main(String[] args) throws IOException {
        LexerAnalyzer lex = new LexerAnalyzer(IOUtils.toString(new FileInputStream("input.txt"), Charset.forName("UTF-8")));
        lex.analyze();
        SyntaxAnalyzer syntaxAnalyzer = new SyntaxAnalyzer(lex.getPairs());
        syntaxAnalyzer.analyze();
    }
}
