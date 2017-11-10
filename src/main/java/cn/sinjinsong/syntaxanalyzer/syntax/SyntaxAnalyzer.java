package cn.sinjinsong.syntaxanalyzer.syntax;

import cn.sinjinsong.syntaxanalyzer.domain.PPT;
import cn.sinjinsong.syntaxanalyzer.domain.Pair;
import cn.sinjinsong.syntaxanalyzer.enumeration.Token;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by SinjinSong on 2017/11/10.
 */
public class SyntaxAnalyzer {
    private List<Pair> tokens;
    private Stack<Pair> stack;
    private List<String> result;
    private PPT ppt;
    private int ptr = 0;
    
    public SyntaxAnalyzer(List<Pair> tokens) {
        this.tokens = tokens;
        this.tokens.add(new Pair(Token.END, "$"));
        //输入串最后加一个$
        this.stack = new Stack<>();
        this.stack.push(new Pair(Token.END,Token.END.symbol));
        this.stack.push(new Pair(Token.PROGRAM,Token.PROGRAM.symbol));
        this.result = new ArrayList<>();
        this.ppt = new PPT();
        ppt.init();
    }

    /**
     * 程序主体
     */
    public void analyze() {
        //当栈顶不是$时
        while(stack.peek().getToken() != Token.END){
            System.out.println("this.input:"+this.input());
            if(stack.peek().getToken() == this.input()){
                System.out.println("匹配!"+this.input());
                stack.pop();
                this.back();
            }else if(!ppt.in(stack.peek().getToken(),this.input())){
                System.err.println("表中无对应，出错");
                System.out.println("栈顶元素为"+stack.peek().getToken());
                System.out.println("输入字符为"+this.input());
            }else{
                //表中有对应
                System.out.println("替换!");
                List<Token> productions = ppt.getProductions(stack.peek().getToken(), this.input());
                stack.pop();
                System.out.println("productions:");
                System.out.println(productions);
                for(int i = productions.size() - 1; i >= 0; --i){
                    stack.push(new Pair(productions.get(i),productions.get(i).symbol));
                }
            }
            System.out.print("stack:");
            System.out.println(stack);
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
