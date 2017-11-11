package cn.sinjinsong.syntaxanalyzer.syntax;

import cn.sinjinsong.syntaxanalyzer.domain.PPT;
import cn.sinjinsong.syntaxanalyzer.domain.Pair;
import cn.sinjinsong.syntaxanalyzer.enumeration.Token;

import java.util.List;
import java.util.Stack;

/**
 * Created by SinjinSong on 2017/11/10.
 */
public class SyntaxAnalyzer {
    private List<Pair> tokens;
    private Stack<Pair> stack;
    private PPT ppt;
    private int ptr = 0;
    
    public SyntaxAnalyzer(List<Pair> tokens) {
        this.tokens = tokens;
        this.tokens.add(new Pair(Token.END, "$"));
        //输入串最后加一个$
        System.out.println("TOKENS");
        System.out.println(this.tokens);
        this.stack = new Stack<>();
        this.stack.push(new Pair(Token.END,Token.END.symbol));
        this.stack.push(new Pair(Token.PROGRAM,Token.PROGRAM.symbol));
        this.ppt = new PPT();
        ppt.init();
    }

    /**
     * 程序主体
     */
    public void analyze() {
        int step = 0;
        //当栈顶不是$时
        while(stack.peek().getToken() != Token.END){
            System.out.println("步骤:"+step);
            step++;
            if(stack.peek().getToken() == this.input()){
                System.out.println("匹配! Token为"+this.input());
                //弹出栈顶
                stack.pop();
                //读头后移
                this.back();
            }else if(!ppt.in(stack.peek().getToken(),this.input())){
                System.err.println("表中无对应，出错");
                System.out.println("栈顶元素为"+stack.peek().getToken());
                System.out.println("输入字符为"+this.input());
            }else{
                //表中有对应
                System.out.print("替换!");
                List<Token> productions = ppt.getProductions(stack.peek().getToken(), this.input());
                stack.pop();
                System.out.print("productions:");
                System.out.print(stack.peek().getToken()+"->");
                System.out.println(productions);
                //产生式右侧入栈
                for(int i = productions.size() - 1; i >= 0; --i){
                    stack.push(new Pair(productions.get(i),productions.get(i).symbol));
                }
            }
            System.out.print("stack:");
            System.out.println(stack);
            System.out.println();
        }
        System.out.println("结束!");
        //结束
    }

    /**
     * 返回读头下面的符号
     * @return
     */
    private Token input(){
        return tokens.get(ptr).getToken();
    }

    /**
     * 读头后移
     */
    public void back(){
        ptr++;
    }
}
