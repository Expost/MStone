package com.company;


import com.sun.org.apache.bcel.internal.classfile.LineNumber;

import java.io.IOException;
import java.io.Reader;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ghost on 2016/12/1.
 */
public class Lexer {

    // 正则表达式，用来分词，详细解释参见书籍
    public static String regexPat
            = "\\s*((//.*)|([0-9]+)|(\"(\\\\\"|\\\\\\\\|\\\\n|[^\"])*\")"
            + "|[A-Z_a-z][A-Z_a-z0-9]*|==|<=|>=|&&|\\|\\||\\p{Punct})?";

    private Pattern pattern = Pattern.compile(regexPat);
    private ArrayList<Token> queue = new ArrayList<Token>();
    private boolean hasMore;
    private LineNumberReader reader;

    public Lexer(Reader r){
        hasMore = true;
        reader = new LineNumberReader(r);
    }
    public Token read() {
        if(fillQueue(0)){
            return queue.remove(0);
        }
        else{
            return Token.EOF;
        }
    }

    // 填充队列满足指定大小
    private boolean fillQueue(int i){
        while(i >= queue.size()){
            if(hasMore)
                readLine();
            else
                return false;
        }
        return true;
    }

    protected void readLine(){
        String line;
        try {
            line = reader.readLine();
        }catch(IOException e){
            return;
        }
        if(line == null){
            hasMore = false;
            return;
        }

        int lineNo = reader.getLineNumber();
        Matcher matcher = pattern.matcher(line);
        matcher.useTransparentBounds(true).useAnchoringBounds(false);
        int pos = 0;
        int endPos = line.length();
        while(pos < endPos){
            matcher.region(pos, endPos);
            if(matcher.lookingAt()){
                addToken(lineNo, matcher);
                pos = matcher.end();
            }
            else{
                return;
            }
        }

        queue.add(new IdToken(lineNo, Token.EOL));
    }

    protected void addToken(int lineNo, Matcher matcher){
        String m = matcher.group(1);
        if (m != null) { // if not a space
            if (matcher.group(2) == null) { // if not a comment
                Token token;
                if (matcher.group(3) != null)
                    token = new NumToken(lineNo, Integer.parseInt(m));
                else if (matcher.group(4) != null)
                    token = new StrToken(lineNo, toStringLiteral(m));
                else
                    token = new IdToken(lineNo, m);
                queue.add(token);
            }
        }
    }

    // 该函数将字符串两边的 \" 去掉
    protected String toStringLiteral(String s){
        StringBuilder sb = new StringBuilder();
        int len = s.length() - 1;
        for(int i = 1; i < len; i++){
            char c = s.charAt(i);
            if(c == '\\' && i + 1 < len){
                int c2 = s.charAt(i + 1);
                if( c2 == '"' || c2 == '\\')
                    c = s.charAt(++i);
                else if(c2 == 'n'){
                    ++i;
                    c = '\n';
                }
            }

            sb.append(c);
        }
        return sb.toString();
    }

    protected static class NumToken extends Token{
        private int value;

        protected NumToken(int line, int v){
            super(line);
            value = v;
        }
        public boolean isNumber(){ return false; }
        public String getText() { return Integer.toString(value); }
        public int getNumber() { return value; }
    }

    protected static class IdToken extends Token{
        private String text;
        protected IdToken(int line, String id){
            super(line);
            text = id;
        }
        public boolean isIdentifier(){ return true; }
        public String getText(){ return text; }
    }

    protected static class StrToken extends Token{
        private String literal;
        StrToken(int line, String str) {
            super(line);
            literal = str;
        }

        public boolean isString() { return true; }
        public String getText() { return literal; }
    }
}
