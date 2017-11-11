package cn.sinjinsong.syntaxanalyzer.domain;

import cn.sinjinsong.syntaxanalyzer.enumeration.Token;
import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Created by SinjinSong on 2017/11/10.
 */
public class PPT {
    private Map<Token, Map<Token, List<Token>>> table;

    public void init() {
        table = new HashMap<>();
        try {
            List<String> lines = IOUtils.readLines(new FileReader("ppt.txt"));
            for (String line : lines) {
                String[] parts = line.split("->");
                String[] leftParts = parts[0].split(",");
                Token nonTerminal = Token.getBySymbol(leftParts[0]);
                Token input = Token.getBySymbol(leftParts[1]);
                if (table.get(nonTerminal) == null) {
                    Map<Token, List<Token>> item = new HashMap<>();
                    table.put(nonTerminal, item);
                }
                if (parts.length == 1) {
                    table.get(nonTerminal).put(input, Collections.EMPTY_LIST);
                } else {
                    String[] rightParts = parts[1].split(" ");
                    List<Token> list = new ArrayList<>();
                    for (String part : rightParts) {
                        if(Token.getBySymbol(part) != null){
                            list.add(Token.getBySymbol(part));
                        }
                    }
                    table.get(nonTerminal).put(input, list);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        for (Map.Entry<Token, Map<Token, List<Token>>> entry : table.entrySet()) {
//            for (Map.Entry<Token, List<Token>> e : entry.getValue().entrySet()) {
//                System.out.print(entry.getKey() + ",");
//                System.out.print(e.getKey() + ":");
//                System.out.println(e.getValue());
//            }
//        }
    }

    public boolean in(Token nonTerminal, Token input) {
        return this.table.get(nonTerminal) != null && this.table.get(nonTerminal).get(input) != null;
    }

    public List<Token> getProductions(Token nonTerminal, Token input) {
        return this.table.get(nonTerminal).get(input);
    }
}
