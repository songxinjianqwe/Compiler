package cn.sinjinsong.syntaxanalyzer.domain;


import cn.sinjinsong.syntaxanalyzer.enumeration.Token;

/**
 * Created by SinjinSong on 2017/10/23.
 */
public class Pair {
    private Token token;
    private String str;

    public Pair(Token token, String str) {
        if (token == null) {
            throw new NullPointerException();
        }
        this.token = token;
        this.str = str;
    }

    public Token getToken() {
        return token;
    }

    public String getStr() {
        return str;
    }

    @Override
    public String toString() {
        return "(" + token +
                "," + str + ')';
    }
}
