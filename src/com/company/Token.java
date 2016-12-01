package com.company;

/**
 * Created by ghost on 2016/12/1.
 */
public abstract class Token {
    public static final Token EOF = new Token(-1){};
    public static final String EOL = "\\n";
    private int lineNumber;

    protected Token(int line){
        lineNumber = line;
    }

    public int getLineNumber(){
        return lineNumber;
    }

    public boolean isIdentifier(){
        return false;
    }

    public boolean isNumber(){
        return  false;
    }

    public boolean isString(){
        return false;
    }

    public int getNumber(){
        return 0;
    }

    public String getText(){
        return "";
    }

}
