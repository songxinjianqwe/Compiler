package cn.sinjinsong.syntaxanalyzer.lexer;

import cn.sinjinsong.syntaxanalyzer.domain.Pair;
import cn.sinjinsong.syntaxanalyzer.enumeration.Token;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.sinjinsong.syntaxanalyzer.util.TypeUtil.*;

/**
 * Created by SinjinSong on 2017/11/10.
 */
public class LexerAnalyzer {
    private List<Pair> tokens;
    private String buffer;
    private int i = 0;
    private char ch;

    public LexerAnalyzer(String buffer) {
        this.buffer = buffer;
        this.tokens = new ArrayList<>();
    }
    
    public void analyze() {
        StringBuilder token = new StringBuilder();
        while (i < buffer.length()) {
            //读入第一个非空白的字符
            this.getFirstNotWhiteSpaceChar();
            //如果读完，那么退出
            if (i > buffer.length()) {
                break;
            }
            //**********************************
            //判断是否是字母
            if (isLetter(ch)) {
                //判断是否是标识符
                while (isLetter(ch) || isDigit(ch)) {
                    token.append(ch);
                    this.getChar();
                }
                //将非字符或数字的字符放回到缓冲区
                this.unGetChar();
                //判断是标识符还是关键字，优先关键字
                if (isKeyWord(token.toString())) {
                    //输出关键字
                    this.tokens.add(new Pair(Token.valueOf(token.toString().toUpperCase()), token.toString()));
                } else {
                    //输出标识符
                    this.tokens.add(new Pair(Token.IDENTIFIER, token.toString()));
                }
                //清空token
                token.delete(0, token.length());
            }
            //**********************************
            //判断是否是数字
            else if (isDigit(ch)) {
                while (isDigit(ch)) {
                    token.append(ch);
                    this.getChar();
                }
                //当读到数字后的第一个字符是字母，那么是非法
                if (isLetter(ch)) {
                    //输出错误
                    this.tokens.add(new Pair(Token.ERROR, token.toString()));
                } else {
                    //放回
                    this.unGetChar();
                    //输出常数
                    this.tokens.add(new Pair(Token.DIGIT, token.toString()));
                }
                token.delete(0, token.length());
            }
            //***********************************
            //判断是否是运算符，这里的operator除了在Token中列出的几种外，还包含了/
            else if (isOperator(ch)) {
                //针对注释单独处理
                //生成的token中不包含注释，要在这里将各种的注释全部去掉
                //************************************************
                if (ch == '/') {
                    this.getChar();
                    //后面的均为 /* */ 多行注释
                    if (ch == '*') {
                        while (true) {
                            this.getChar();
                            if (ch == '*') {// 为多行注释结束
                                this.getChar();
                                if (ch == '/') {
                                    this.getChar();
                                    break;
                                }
                            }
                        }
                        continue;
                    }
                    //后面的为单行注释
                    if (ch == '/') {
                        while (ch != '\n') {
                            this.getChar();
                        }
                        continue;
                    }
                    //如果不是注释，那么要把88行多取到的字符放回
                    this.unGetChar();
                }
                //************************************************
                //判断组合运算符
                if (ch == '>' || ch == '<' || ch == '=' || ch == '!') {
                    char prev = ch;
                    this.getChar();
                    if (ch == '=') {
                        this.tokens.add(new Pair(Token.getBySymbol(prev + "="), prev + "="));
                        continue;
                    }
                    this.unGetChar();
                }

                this.tokens.add(new Pair(Token.getBySymbol(String.valueOf(ch)), String.valueOf(ch)));
            }
            //*************************************
            //判断是否是界符
            else if (isSeparators(ch)) {
                this.tokens.add(new Pair(Token.getBySymbol(String.valueOf(ch)), String.valueOf(ch)));
            }
            //**************************************
            //处理异常情况
            else {
                this.tokens.add(new Pair(Token.ERROR, String.valueOf(ch)));
            }
        }
    }

    /**
     * 从缓冲区中读入一个字符
     */
    private void getChar() {
        this.ch = this.buffer.charAt(i);
        i++;
    }

    /**
     * 将刚刚读入的字符放回到缓冲区
     */
    private void unGetChar() {
        i--;
        this.ch = this.buffer.charAt(i - 1);
    }

    /**
     * 确定指定字符依据 Java 标准是否为空白字符
     */
    private void getFirstNotWhiteSpaceChar() {
        do {
            getChar();
        }
        while (Character.isWhitespace(ch) && i < this.buffer.length());
    }

    public List<Token> getTokens() {
        return this.tokens.stream().map(Pair::getToken).collect(Collectors.toList());
    }
    
    public List<Pair> getPairs(){
        return this.tokens;
    }

    public void print() {
        for (Pair pair : tokens) {
            System.out.println(pair);
        }
    }

    public void toFile(String fileName) throws IOException {
        File file = new File(fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Pair pair : tokens) {
            bw.write(pair.toString());
            bw.newLine();
        }
        bw.close();
    }
}
