package com.company;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String s =
                "while i < 10{\n" +
                "sum = sum + i\n" +
                "i = i + 1\n" +
                "}\n" +
                        "sum\n";
//
        Lexer l = new Lexer(new StringReader(s));
        for(Token t; (t = l.read()) != Token.EOF;){
            System.out.println("=> " + t.getText());
        }


//        Pattern pattern = Pattern.compile("\\s*([A-Z]+)|([0-9]+)");
//        Matcher matcher = pattern.matcher(" 10");
//        System.out.println(matcher.find());
//        String m = matcher.group(2);
//        System.out.println(m);
    }
}
